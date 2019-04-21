package lesson24.touragency.city.repo.jdbc;

import lesson24.touragency.city.domain.City;
import lesson24.touragency.city.domain.CityDiscriminator;
import lesson24.touragency.city.domain.ColdCity;
import lesson24.touragency.city.domain.HotCity;
import lesson24.touragency.city.exception.unchecked.UnknownCityDiscriminatorException;
import lesson24.touragency.city.repo.CityRepo;
import lesson24.touragency.city.search.CitySearchCondition;
import lesson24.touragency.common.business.database.datasource.HikariCpDataSource;
import lesson24.touragency.common.business.exception.jdbc.KeyGenerationError;
import lesson24.touragency.common.business.exception.jdbc.SqlError;
import lesson24.touragency.common.business.repo.jdbc.SqlPreparedStatementConsumerHolder;
import lesson24.touragency.common.solution.repo.jdbc.PreparedStatementConsumer;
import lesson24.touragency.common.solution.repo.jdbc.QueryWrapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static java.sql.Types.INTEGER;
import static lesson24.touragency.city.domain.CityDiscriminator.COLD;
import static lesson24.touragency.city.repo.jdbc.CityMapper.mapCold;
import static lesson24.touragency.city.repo.jdbc.CityMapper.mapHot;
import static org.h2.value.Value.STRING;

public class CityDefaultRepoImpl implements CityRepo {
    @Override
    public List<? extends City> search(CitySearchCondition searchCondition) {
        try {
            SqlPreparedStatementConsumerHolder sqlParamsHolder = getSearchSqlAndPrStmtHolder(searchCondition);

            return QueryWrapper.select(sqlParamsHolder.getSql(),
                    (rs) -> {
                        String discriminatorStr = rs.getString("DISCRIMINATOR");
                        return CityDiscriminator.getDiscriminatorByName(discriminatorStr)
                                .map(discriminator -> COLD.equals(discriminator) ? mapCold(rs) : mapHot(rs))
                                .orElseThrow(UnknownCityDiscriminatorException::new);
                    },
                    ps -> {
                        List<PreparedStatementConsumer> psConsumers = sqlParamsHolder.getPreparedStatementConsumers();
                        for (PreparedStatementConsumer consumer : psConsumers) {
                            consumer.consume(ps);
                        }
                    }
            );
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    private SqlPreparedStatementConsumerHolder getSearchSqlAndPrStmtHolder(CitySearchCondition searchCondition) {
        StringBuilder sql = new StringBuilder("SELECT * FROM CITY");

        List<PreparedStatementConsumer> psConsumers = new ArrayList<>();

        if (searchCondition.getId() != null) {
            sql.append(" WHERE ID = ?");
            psConsumers.add(ps -> ps.setLong(1, searchCondition.getId()));
        } else {
            StringBuilder conditions = new StringBuilder();

            if (searchCondition.getCityDiscriminator() != null) {
                conditions.append(" WHERE (DISCRIMINATOR = ?)");
                psConsumers.add(ps -> ps.setString(1, searchCondition.getCityDiscriminator().toString()));
            }
            sql.append(conditions.toString());
        }

        return new SqlPreparedStatementConsumerHolder(sql.toString(), psConsumers);
    }


    @Override
    public City add(City city) {
        try {
            Optional<Long> generatedId = QueryWrapper.executeUpdateReturningGeneratedKey(getInsertCitySql(),
                    ps -> {
                        AtomicInteger index = new AtomicInteger(0);
                        appendPreparedStatementParamsForCity(ps, city, index);
                        switch (city.getDiscriminator()) {

                            case COLD: {
                                appendPreparedStatementParamsForColdCity(ps, (ColdCity) city, index);
                                break;
                            }
                            case HOT: {
                                appendPreparedStatementParamsForHotCity(ps, (HotCity) city, index);
                                break;
                            }
                        }
                    },
                    rs -> rs.getLong("ID"));

            if (generatedId.isPresent()) {
                city.setId(generatedId.get());
            } else {
                throw new KeyGenerationError("ID");
            }

            return city;
        } catch (KeyGenerationError e) {
            throw e;
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    private String getInsertCitySql() {
        return "INSERT INTO CITY (" +
                "COUNTRY_ID," +
                "NAME," +
                "POPULATION," +
                "DISCRIMINATOR," +
                "COLDEST_TEMP," +
                "COLDEST_MONTH," +
                "HOTTEST_MONTH," +
                "HOTTEST_TEMP" +
                ")" +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    }

    @Override
    public void add(Collection<City> cities) {
        try {
            QueryWrapper.executeUpdateAsBatch(getInsertCitySql(), cities,
                    (ps, city) -> {
                        AtomicInteger index = new AtomicInteger(0);
                        appendPreparedStatementParamsForCity(ps, city, index);
                        switch (city.getDiscriminator()) {

                            case COLD: {
                                appendPreparedStatementParamsForColdCity(ps, (ColdCity) city, index);
                                break;
                            }
                            case HOT: {
                                appendPreparedStatementParamsForHotCity(ps, (HotCity) city, index);
                                break;
                            }
                        }
                    });
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public void update(City city) {
        try {
            String sql =
                    "UPDATE CITY " +
                            "SET " +
                            "COUNTRY_ID = ?," +
                            "NAME = ? ," +
                            "POPULATION = ?," +
                            "DISCRIMINATOR = ? ," +
                            "COLDEST_TEMP = ? ," +
                            "COLDEST_MONTH = ? ," +
                            "HOTTEST_TEMP = ?," +
                            "HOTTEST_MONTH = ?    " +
                            " WHERE ID = ? ";

            QueryWrapper.executeUpdate(sql, ps -> {
                AtomicInteger index = new AtomicInteger(0);
                appendPreparedStatementParamsForCity(ps, city, index);

                switch (city.getDiscriminator()) {

                    case COLD: {
                        appendPreparedStatementParamsForColdCity(ps, (ColdCity) city, index);
                        break;
                    }
                    case HOT: {
                        appendPreparedStatementParamsForHotCity(ps, (HotCity) city, index);
                        break;
                    }
                }
                ps.setLong(index.incrementAndGet(), city.getId());
            });
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    private void appendPreparedStatementParamsForCity(PreparedStatement ps, City city, AtomicInteger index) throws SQLException {
        ps.setLong(index.incrementAndGet(), city.getCountryId());
        ps.setString(index.incrementAndGet(), city.getName());
        ps.setInt(index.incrementAndGet(), city.getPopulation());
        //???
        CityDiscriminator discriminator = city.getDiscriminator();
        ps.setString(index.incrementAndGet(), discriminator.toString());
    }

    private void appendPreparedStatementParamsForColdCity(PreparedStatement ps, ColdCity cold, AtomicInteger index) throws SQLException {
        ps.setInt(index.incrementAndGet(), cold.getColdestTemp());
        ps.setString(index.incrementAndGet(), cold.getColdestMonth());
        ps.setNull(index.incrementAndGet(), INTEGER);
        ps.setNull(index.incrementAndGet(), STRING);
        //???
    }

    private void appendPreparedStatementParamsForHotCity(PreparedStatement ps, HotCity hot, AtomicInteger index) throws SQLException {
        ps.setNull(index.incrementAndGet(), INTEGER);
        ps.setNull(index.incrementAndGet(), STRING);
        ps.setInt(index.incrementAndGet(), hot.getHottestTemp());
        ps.setString(index.incrementAndGet(), hot.getHottestMonth());
    }

    @Override
    public Optional<City> findById(Long id) {
        try {
            return QueryWrapper.selectOne("SELECT * FROM CITY WHERE ID = ?",

                    (rs) -> {
                        String discriminatorStr = rs.getString("DISCRIMINATOR");
                        return CityDiscriminator.getDiscriminatorByName(discriminatorStr)
                                .map(discriminator -> COLD.equals(discriminator) ? mapCold(rs) : mapHot(rs))
                                .orElseThrow(UnknownCityDiscriminatorException::new);
                    },

                    ps -> {
                        ps.setLong(1, id);
                    });
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            QueryWrapper.executeUpdate("DELETE FROM CITY WHERE ID = ?", ps -> {
                ps.setLong(1, id);
            });
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public void printAll() {
        findAll().forEach(System.out::println);
    }

    @Override
    public List<City> findAll() {
        try {
            return QueryWrapper.select("SELECT * FROM CITY",
                    (rs) -> {
                        String discriminatorStr = rs.getString("DISCRIMINATOR");
                        return CityDiscriminator.getDiscriminatorByName(discriminatorStr)
                                .map(discriminator -> COLD.equals(discriminator) ? mapCold(rs) : mapHot(rs))
                                .orElseThrow(UnknownCityDiscriminatorException::new);
                    }
            );
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public int countAll() {
        try {
            return QueryWrapper.selectOne("SELECT COUNT(*) AS CNT FROM CITY",
                    rs -> rs.getInt("CNT")).orElse(0);
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public List<City> getCitiesByCountryId(long countryId) {
        try {
            String sql = "SELECT * FROM CITY WHERE COUNTRY_ID = ?";
            return QueryWrapper.select(sql,
                    rs -> {
                        String discriminatorStr = rs.getString("DISCRIMINATOR");
                        return CityDiscriminator.getDiscriminatorByName(discriminatorStr)
                                .map(discriminator -> COLD.equals(discriminator) ? mapCold(rs) : mapHot(rs))
                                .orElseThrow(UnknownCityDiscriminatorException::new);
                    },
                    ps -> {
                        ps.setLong(1, countryId);
                    }
            );
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    private Connection getConnection() {
        return HikariCpDataSource.getInstance().getConnection();
    }
}
