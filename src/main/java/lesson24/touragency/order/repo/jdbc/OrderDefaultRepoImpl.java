package lesson24.touragency.order.repo.jdbc;

import lesson24.touragency.city.domain.City;
import lesson24.touragency.city.domain.CityDiscriminator;
import lesson24.touragency.city.repo.jdbc.CityMapper;
import lesson24.touragency.common.business.exception.jdbc.KeyGenerationError;
import lesson24.touragency.common.business.exception.jdbc.SqlError;
import lesson24.touragency.common.business.repo.jdbc.SqlPreparedStatementConsumerHolder;
import lesson24.touragency.common.solution.repo.jdbc.PreparedStatementConsumer;
import lesson24.touragency.common.solution.repo.jdbc.QueryWrapper;
import lesson24.touragency.common.solution.repo.jdbc.ResultSetExtractor;
import lesson24.touragency.country.domain.Country;
import lesson24.touragency.country.repo.jdbc.CountryMapper;
import lesson24.touragency.order.domain.Order;
import lesson24.touragency.order.repo.OrderRepo;
import lesson24.touragency.order.search.OrderSearchCondition;
import lesson24.touragency.user.domain.User;
import lesson24.touragency.user.repo.jdbc.UserMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderDefaultRepoImpl implements OrderRepo {
    @Override
    public List<Order> search(OrderSearchCondition searchCondition) {
        try {
            SqlPreparedStatementConsumerHolder selectDataHolder = getSearchSqlAndPrStmtHolder(searchCondition);
            return QueryWrapper.select(selectDataHolder.getSql(), OrderMapper::mapOrder,
                    ps -> {
                        for (PreparedStatementConsumer statementConsumer : selectDataHolder.getPreparedStatementConsumers()) {
                            statementConsumer.consume(ps);
                        }
                    });
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    private SqlPreparedStatementConsumerHolder getSearchSqlAndPrStmtHolder(OrderSearchCondition searchCondition) {
        String sql = "SELECT * FROM ORDER_TAB ";

        List<PreparedStatementConsumer> psConsumers = new ArrayList<>();

        if (searchCondition.getId() != null) {
            sql = sql + " WHERE ID = ?";
            psConsumers.add(ps -> ps.setLong(1, searchCondition.getId()));
        } else {
            AtomicInteger index = new AtomicInteger(0);
            List<String> where = new ArrayList<>();

            if (searchCondition.searchByCountryId()) {
                where.add("(COUNTRY_ID = ?)");
                psConsumers.add(ps -> ps.setLong(index.incrementAndGet(), searchCondition.getCountryId()));
            }

            if (searchCondition.searchByCityId()) {
                where.add("(CITY_ID = ?)");
                psConsumers.add(ps -> ps.setLong(index.incrementAndGet(), searchCondition.getCityId()));
            }

            if (searchCondition.searchByUserId()) {
                where.add("(USER_ID = ?)");
                psConsumers.add(ps -> ps.setLong(index.incrementAndGet(), searchCondition.getUserId()));
            }

            String whereStr = String.join("AND ", where);
            sql = sql + (whereStr.isEmpty() ? "" : " WHERE " + whereStr);

            if (searchCondition.shouldPaginate()) {
                sql = sql + getPagebleSqlPart(searchCondition);
            }
        }

        return new SqlPreparedStatementConsumerHolder(sql, psConsumers);
    }

    private String getPagebleSqlPart(OrderSearchCondition orderSearchCondition) {
        return " LIMIT " + orderSearchCondition.getPaginator().getLimit() + " OFFSET " + orderSearchCondition.getPaginator().getOffset();
    }


    @Override
    public int countByCity(long cityId) {
        try {
            return QueryWrapper.selectOne("SELECT COUNT(*) AS CNT FROM ORDER_TAB WHERE CITY_ID = ?",
                    rs -> rs.getInt("CNT"),
                    ps -> {
                        ps.setLong(1, cityId);
                    }).orElse(0);
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public int countByCountry(long countryId) {
        try {
            return QueryWrapper.selectOne("SELECT COUNT(*) AS CNT FROM ORDER_TAB WHERE COUNTRY_ID = ?",
                    rs -> rs.getInt("CNT"),
                    ps -> {
                        ps.setLong(1, countryId);
                    }).orElse(0);
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public void deleteByUserId(long userId) {
        try {
            QueryWrapper.executeUpdate("DELETE FROM ORDER_TAB WHERE USER_ID = ?",
                    ps -> {
                        ps.setLong(1, userId);
                    });
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public List<Order> findByUserId(long userId) {
        try {
            String sql = "SELECT * FROM ORDER_TAB WHERE USER_ID = ?";
            return QueryWrapper.select(sql, OrderMapper::mapOrder, ps -> {
                ps.setLong(1, userId);
            });
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public Order add(Order order) {
        return add(order, null);
    }

    @Override
    public Order insertTx(Order order, Connection connection) {
        return add(order, connection);
    }

    private Order add(Order order, Connection connection) {
        try {

            Optional<Long> optionalId;
            PreparedStatementConsumer psConsumer = ps -> appendPsValuesForInsertOrder(ps, order);
            ResultSetExtractor<Long> rsExtractor = rs -> rs.getLong("ID");

            if (connection == null) {
                optionalId = QueryWrapper.executeUpdateReturningGeneratedKey(getInsertOrderSql(), psConsumer, rsExtractor);
            } else {
                optionalId = QueryWrapper.executeUpdateReturningGeneratedKey(getInsertOrderSql(), connection, psConsumer, rsExtractor);
            }

            if (optionalId.isPresent()) {
                order.setId(optionalId.get());
            } else {
                throw new KeyGenerationError("ID");
            }
            return order;
        } catch (KeyGenerationError e) {
            throw e;
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    private void appendPsValuesForInsertOrder(PreparedStatement ps, Order order) throws SQLException {
        int index = 0;
        ps.setLong(++index, order.getUser().getId());
        ps.setLong(++index, order.getCountry().getId());
        ps.setLong(++index, order.getCity().getId());
        ps.setString(++index, order.getDescription());
        ps.setInt(++index, order.getPrice());
    }

    private String getInsertOrderSql() {
        return "INSERT INTO ORDER_TAB (USER_ID, COUNTRY_ID, CITY_ID, DESCRIPTION, PRICE) VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    public void add(Collection<Order> orders) {
        try {
            QueryWrapper.executeUpdateAsBatch(getInsertOrderSql(), orders, this::appendPsValuesForInsertOrder);
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public void update(Order order) {
        throw new UnsupportedOperationException("No update operation for order");
    }

    @Override
    public Optional<Order> findById(Long id) {
        try {
            String sql = "SELECT * FROM ORDER_TAB WHERE ID = ?";
            return QueryWrapper.selectOne(sql, OrderMapper::mapOrder, ps -> {
                ps.setLong(1, id);
            });
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public Optional<Order> getFullOrder(long id) {
        try {
            String sql = "SELECT " +
                    "ordr.ID AS ORDER_IDENT, " +
                    "ordr.*, " +
                    "mk.ID AS COUNTRY_IDENT, " +
                    "mk.NAME AS COUNTRY_NAME, " +
                    "mk.*, " +
                    "md.ID AS CITY_IDENT," +
                    "md.NAME AS CITY_NAME, " +
                    "md.*, " +
                    "u.*, " +
                    "u.ID AS USER_IDENT " +
                    "FROM " +
                    "ORDER_TAB ordr " +
                    "INNER JOIN COUNTRY mk ON (mk.ID = ordr.COUNTRY_ID) " +
                    "INNER JOIN CITY md ON (md.ID = ordr.CITY_ID) " +
                    "INNER JOIN USER u ON (u.ID = ordr.USER_ID) " +
                    "WHERE ordr.ID = ?";

            return QueryWrapper.selectOne(sql, rs -> {
                Order order = OrderMapper.mapOrder(rs);
                order.setId(rs.getLong("ORDER_IDENT"));

                order.setCountry(getCountryForOrderFullRequest(rs));
                order.setCity(getCityForOrderFullRequest(rs));
                order.setUser(getUserForOrderFullRequest(rs));
                return order;
            }, ps -> {
                ps.setLong(1, id);
            });
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    private User getUserForOrderFullRequest(ResultSet rs) throws SQLException {
        User user = UserMapper.mapUser(rs);
        user.setId(rs.getLong("USER_IDENT"));
        return user;
    }

    private Country getCountryForOrderFullRequest(ResultSet rs) throws SQLException {
        Country country = CountryMapper.mapCountry(rs);
        country.setName(rs.getString("COUNTRY_NAME"));
        country.setId(rs.getLong("COUNTRY_IDENT"));
        return country;
    }

    private City getCityForOrderFullRequest(ResultSet rs) throws SQLException {
        CityDiscriminator discriminator = CityDiscriminator.valueOf(rs.getString("DISCRIMINATOR"));
        City city;
        if (CityDiscriminator.COLD.equals(discriminator)) {
            city = CityMapper.mapCold(rs);
        } else {
            city = CityMapper.mapHot(rs);
        }
        city.setId(rs.getLong("CITY_IDENT"));
        city.setName(rs.getString("CITY_NAME"));

        return city;
    }

    @Override
    public void deleteById(Long id) {
        deleteById(id, null);
    }

    @Override
    public void deleteByIdTx(long id, Connection connection) {
        deleteById(id, connection);
    }

    private void deleteById(Long id, Connection connection) {
        try {
            String sql = "DELETE FROM ORDER_TAB WHERE ID = ?";
            PreparedStatementConsumer psConsumer = ps -> ps.setLong(1, id);
            if (connection == null) {
                QueryWrapper.executeUpdate(sql, psConsumer);
            } else {
                QueryWrapper.executeUpdate(sql, connection, psConsumer);
            }
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public void printAll() {
        findAll().forEach(System.out::println);
    }

    @Override
    public List<Order> findAll() {
        try {
            String sql = "SELECT * FROM ORDER_TAB ";
            return QueryWrapper.select(sql, OrderMapper::mapOrder);
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public int countAll() {
        try {
            return QueryWrapper.selectOne("SELECT COUNT(*) AS CNT FROM ORDER_TAB",
                    (rs) -> rs.getInt("CNT")).orElse(0);
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }
}
