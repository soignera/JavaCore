package lesson24.touragency.city.service;

import lesson24.touragency.city.domain.City;
import lesson24.touragency.city.search.CitySearchCondition;
import lesson24.touragency.common.solution.BaseService;

import java.util.List;

public interface CityService extends BaseService<City, Long> {
    List<? extends City> search(CitySearchCondition searchCondition);
    List<City> getCitiesByCountryId(Long countryId);
}
