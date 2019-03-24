package lesson11.touragency.city.service;

import lesson11.touragency.city.domain.City;
import lesson11.touragency.city.search.CitySearchCondition;
import lesson11.touragency.common.solution.BaseService;

import java.util.List;

public interface CityService extends BaseService<City, Long> {
    List<? extends City> search(CitySearchCondition searchCondition);
}
