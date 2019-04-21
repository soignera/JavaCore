package lesson24.touragency.country.repo.jdbc;

import lesson24.touragency.city.domain.City;
import lesson24.touragency.city.domain.CityDiscriminator;
import lesson24.touragency.city.repo.jdbc.CityMapper;
import lesson24.touragency.common.business.exception.jdbc.KeyGenerationError;
import lesson24.touragency.common.business.exception.jdbc.SqlError;
import lesson24.touragency.common.business.repo.jdbc.SqlPreparedStatementConsumerHolder;
import lesson24.touragency.common.business.search.OrderDirection;
import lesson24.touragency.common.business.search.OrderType;
import lesson24.touragency.common.solution.repo.jdbc.PreparedStatementConsumer;
import lesson24.touragency.common.solution.repo.jdbc.QueryWrapper;
import lesson24.touragency.country.domain.Country;
import lesson24.touragency.country.repo.CountryRepo;
import lesson24.touragency.country.search.CountrySearchCondition;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CountryDefaultRepoImpl implements CountryRepo {
    private static final List<String> COMPLEX_ORDER_FIELDS = Arrays.asList("COUNTRY", "NAME");

    @Override
    public List<Country> search(CountrySearchCondition searchCondition) {
        try {
            //SELECT * FROM PERSON WHERE (AGE = ?) AND (NAME = ?)
            //ps.set(1, 30)
            //ps.set(2, "Dima")
            //ps.set(10, "Petr")

            //SELECT * FROM PERSON WHERE (AGE = :age) AND (NAME = :name)
            //map.put("age", 30)
            //map.put("name", "Dima")

            //SELECT * FROM PERSON WHERE (AGE = ?) AND (NAME = ?)

            SqlPreparedStatementConsumerHolder sqlParamsHolder = getSearchSqlAndPrStmtHolder(searchCondition);

            return QueryWrapper.select(sqlParamsHolder.getSql(),
                    CountryMapper::mapCountry,
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

    private SqlPreparedStatementConsumerHolder getSearchSqlAndPrStmtHolder(CountrySearchCondition searchCondition) {
        String sql = "SELECT * FROM COUNTRY";

        List<PreparedStatementConsumer> psConsumers = new ArrayList<>();

        if (searchCondition.getId() != null) {
            sql = sql + " WHERE ID = ?";
            psConsumers.add(ps -> ps.setLong(1, searchCondition.getId()));
        } else {
            AtomicInteger index = new AtomicInteger(0);
            List<String> where = new ArrayList<>();

            if (searchCondition.searchByLanguag()) {
                where.add("(LANGUAG = ?)");
                psConsumers.add(ps -> ps.setString(index.incrementAndGet(), searchCondition.getLanguag()));
            }

            if (searchCondition.searchByName()) {
                where.add("(NAME = ?)");
                psConsumers.add(ps -> ps.setString(index.incrementAndGet(), searchCondition.getName()));
            }
            String whereStr = String.join(" AND ", where);
            //(NAME =?) AND (COUNTRY = ?)
            //SELECT * FROM MARK WHERE (NAME =?) AND (COUNTRY = ?)
            sql = sql + (whereStr.isEmpty() ? "" : " WHERE " + whereStr) + getOrdering(searchCondition);
            //SELECT * FROM MARK WHERE (NAME =?) AND (COUNTRY = ?) ORDER BY NAME,COUNTRY ASC

            if (searchCondition.shouldPaginate()){
                sql = sql + getPagebleSqlPart(searchCondition);
            }
        }

        return new SqlPreparedStatementConsumerHolder(sql, psConsumers);
    }

    private String getPagebleSqlPart(CountrySearchCondition countrySearchCondition){
        return " LIMIT " + countrySearchCondition.getPaginator().getLimit() + " OFFSET " + countrySearchCondition.getPaginator().getOffset();
    }

    private String getOrdering(CountrySearchCondition searchCondition) {

        if (searchCondition.needOrdering()) {
            OrderType orderType = searchCondition.getOrderType();
            OrderDirection orderDirection = searchCondition.getOrderDirection();

            switch (orderType) {

                case SIMPLE: {
                    return " ORDER BY " + searchCondition.getOrderByField().name() + " " + orderDirection;
                    //ORDER BY NAME ASC/DESC
                }
                case COMPLEX: {
                    return " ORDER BY " + String.join(", ", COMPLEX_ORDER_FIELDS) + " " + orderDirection;
                    // ORDER BY NAME,COUNTRY ASC/DESC
                }
            }
        }

        return "";
    }

    @Override
    public Country add(Country country) {
        try {
            Optional<Long> generatedId = QueryWrapper.executeUpdateReturningGeneratedKey(getInsertCountrySql(),
                    ps -> {
                        appendPreparedStatementParamsForCountry(new AtomicInteger(0), ps, country);
                    },
                    rs -> rs.getLong("ID"));

            if (generatedId.isPresent()) {
                country.setId(generatedId.get());
            } else {
                throw new KeyGenerationError("ID");
            }

            return country;
        } catch (KeyGenerationError e) {
            throw e;
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    private String getInsertCountrySql() {
        return "INSERT INTO COUNTRY ( NAME, LANGUAG ) VALUES (?, ?)";
    }

    private void appendPreparedStatementParamsForCountry(AtomicInteger index, PreparedStatement ps, Country country) throws SQLException {
        ps.setString(index.incrementAndGet(), country.getName());
        ps.setString(index.incrementAndGet(), country.getLanguag());
    }

    @Override
    public void add(Collection<Country> countries) {
        try {
            QueryWrapper.executeUpdateAsBatch(getInsertCountrySql(), countries,
                    (ps, country) -> appendPreparedStatementParamsForCountry(new AtomicInteger(0), ps, country));
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public void update(Country country) {
        String sql = "UPDATE COUNTRY SET NAME = ?, LANGUAG = ?, WHERE ID = ?";

        try {
            QueryWrapper.executeUpdate(sql,
                    ps -> {
                        AtomicInteger index = new AtomicInteger();
                        appendPreparedStatementParamsForCountry(index, ps, country);
                        ps.setLong(index.incrementAndGet(), country.getId());
                    });
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public Optional<Country> findById(Long id) {
        try {
            String sql = "SELECT * FROM COUNTRY WHERE ID = ?";
            return QueryWrapper.selectOne(sql,
                    CountryMapper::mapCountry,
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
            QueryWrapper.executeUpdate("DELETE FROM COUNTRY WHERE ID = ?", ps -> {
                ps.setLong(1, id);
            });
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public void printAll() {
        findAllCountriesFetchingCities().forEach(System.out::println);
    }

    @Override
    public List<Country> findAll() {
        try {
            return QueryWrapper.select("SELECT * FROM COUNTRY", CountryMapper::mapCountry);
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public int countAll() {
        try {
            return QueryWrapper.selectOne("SELECT COUNT(*) AS CNT FROM COUNTRY",
                    (rs) -> rs.getInt("CNT")).orElse(0);
        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    @Override
    public List<Country> findAllCountriesFetchingCities() {
        try {
            String sql =
                    "SELECT \n" +
                            " mk.ID as COUNTRY_IDENT, \n" +
                            " md.ID as CITY_IDENT, \n" +
                            " mk.NAME as COUNTRY_NAME, \n" +
                            " md.NAME as CITY_NAME, \n" +
                            " mk.*, md.* \n" +
                            " \n" +
                            " FROM COUNTRY mk \n" +
                            " LEFT JOIN CITY md ON (mk.ID = md.COUNTRY_ID)";
            /**
             *  1 | Vaz | 1 | 2106
             *  1 | Vaz | 2 | 21099
             *
             *  2 | BMW | 3 | X5
             *  2 | BMW | 4 | X3
             *
             *  3 | CAT | NULL | NULL
             */

            return QueryWrapper.select(sql, (rs, accumulator) -> {

                Map<Long, Country> countriesMap = new LinkedHashMap<>();

                while (rs.next()) {
                    long countryId = rs.getLong("COUNTRY_IDENT");

                    if (!countriesMap.containsKey(countryId)) {
                        Country country = getCountryForFindAllCountriesFetchingCitiesQuery(rs, countryId);
                        countriesMap.put(countryId, country);
                        accumulator.add(country);
                    }

                    Country country = countriesMap.get(countryId);

                    String discrStr = rs.getString("DISCRIMINATOR");
                    if (discrStr != null) {
                        City city = getCityForFindAllCountriesFetchingCitiesQuery(rs, discrStr);

                        if (country.getCities() == null) {
                            country.setCities(new ArrayList<>());
                        }
                        country.getCities().add(city);
                    }
                }
            });

        } catch (Exception e) {
            throw new SqlError(e);
        }
    }

    private Country getCountryForFindAllCountriesFetchingCitiesQuery(ResultSet rs, long countryId) throws SQLException{
        Country country = CountryMapper.mapCountry(rs);
        country.setId(countryId);
        country.setName(rs.getString("COUNTRY_NAME"));
        return country;
    }

    private City getCityForFindAllCountriesFetchingCitiesQuery(ResultSet rs, String discrStr) throws SQLException{
        CityDiscriminator discriminator = CityDiscriminator.valueOf(discrStr);

        City city;
        if (CityDiscriminator.COLD.equals(discriminator)) {
            city = CityMapper.mapCold(rs);
        } else {
            city = CityMapper.mapHot(rs);
        }

        CityMapper.mapCommonCityData(city, rs);
        city.setId(rs.getLong("CITY_IDENT"));
        city.setName(rs.getString("CITY_NAME"));

        return city;
    }
}
