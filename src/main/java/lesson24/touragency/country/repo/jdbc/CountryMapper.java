package lesson24.touragency.country.repo.jdbc;

import lesson24.touragency.common.business.exception.jdbc.ResultSetMappingException;
import lesson24.touragency.country.domain.Country;

import java.sql.ResultSet;

public final class CountryMapper {
    private static final String COUNTRY_CLASS_NAME = Country.class.getSimpleName();

    private CountryMapper() {
    }

    public static Country mapCountry(ResultSet rs) throws ResultSetMappingException {
        try {
            Country country = new Country();
            country.setId(rs.getLong("ID"));
            country.setName(rs.getString("NAME"));
            country.setLanguag(rs.getString("LANGUAG"));

            return country;
        } catch (Exception e) {
            throw new ResultSetMappingException(COUNTRY_CLASS_NAME, e);
        }
    }
}
