package lesson24.touragency.city.repo.jdbc;

import lesson24.touragency.city.domain.City;
import lesson24.touragency.city.domain.ColdCity;
import lesson24.touragency.city.domain.HotCity;
import lesson24.touragency.common.business.exception.jdbc.ResultSetMappingException;

import java.sql.ResultSet;

public final class CityMapper {
    private static final String CITY_CLASS_NAME = City.class.getSimpleName();

    private CityMapper() {

    }

    public static HotCity mapHot(ResultSet rs) throws ResultSetMappingException {
        try {
            HotCity hotCity = new HotCity();
            hotCity.setHottestTemp(rs.getInt("HOTTEST_TEMP"));
            hotCity.setHottestMonth(rs.getString("HOTTEST_MONTH"));

            mapCommonCityData(hotCity, rs);
            return hotCity;
        } catch (Exception e) {
            throw new ResultSetMappingException(CITY_CLASS_NAME, e);
        }
    }

    public static ColdCity mapCold(ResultSet rs) throws ResultSetMappingException {
        try {

            ColdCity coldCity = new ColdCity();
            coldCity.setColdestMonth(rs.getString("COLDEST_MONTH"));
            coldCity.setColdestTemp(rs.getInt("COLDEST_TEMP"));
            mapCommonCityData(coldCity, rs);

            return coldCity;
        } catch (Exception e) {
            throw new ResultSetMappingException(CITY_CLASS_NAME, e);
        }

    }

    public static void mapCommonCityData(City city, ResultSet rs) throws ResultSetMappingException {
        try {
            city.setId(rs.getLong("ID"));
            city.setName(rs.getString("NAME"));
            city.setCountryId(rs.getLong("COUNTRY_ID"));
            city.setPopulation(rs.getInt("POPULATION"));
            //????
        } catch (Exception e) {
            throw new ResultSetMappingException(CITY_CLASS_NAME, e);
        }
    }
}
