package lesson17.touragency.city.service;

import lesson17.touragency.city.domain.City;
import lesson17.touragency.city.search.CitySearchCondition;
import lesson17.touragency.common.solution.BaseService;

import java.util.List;

public interface CityService extends BaseService<City, Long> {
    List<? extends City> search(CitySearchCondition searchCondition);
}
