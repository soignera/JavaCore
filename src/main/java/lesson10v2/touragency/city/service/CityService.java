package lesson10v2.touragency.city.service;

import lesson10v2.touragency.city.domain.City;
import lesson10v2.touragency.city.search.CitySearchCondition;
import lesson10v2.touragency.common.solution.BaseService;

import java.util.List;

public interface CityService extends BaseService<City, Long> {
    void add(City city);

    City findById(Long id);
    void delete(City city);
    List<City> search(CitySearchCondition searchCondition);
    //City[] search(CitySearchCondition searchCondition);

    void update(City city);
}
