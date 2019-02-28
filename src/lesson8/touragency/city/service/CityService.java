package lesson8.touragency.city.service;

import lesson8.touragency.city.domain.City;
import lesson8.touragency.city.search.CitySearchCondition;
import lesson8.touragency.common.business.service.BaseService;

public interface CityService extends BaseService {
    void add(City city);

    City findById(Long id);
    void delete(City city);

    City[] search(CitySearchCondition searchCondition);
}
