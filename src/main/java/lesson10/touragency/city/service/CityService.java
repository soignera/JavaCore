package lesson10.touragency.city.service;

import lesson10.touragency.city.domain.City;
import lesson10.touragency.city.search.CitySearchCondition;
import lesson10.touragency.common.business.service.BaseService;

import java.util.List;

public interface CityService extends BaseService {
    void add(City city);

    City findById(Long id);
    void delete(City city);
    List<City> search(CitySearchCondition searchCondition);
    //City[] search(CitySearchCondition searchCondition);

    void update(City city);
}
