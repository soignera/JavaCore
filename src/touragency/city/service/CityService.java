package touragency.city.service;

import touragency.city.City;

import touragency.city.search.CitySearchCondition;
import touragency.common.business.service.BaseService;

public interface CityService extends BaseService {
    void add(City city);

    City findById(Long id);
    void delete(City city);

    City[] search(CitySearchCondition searchCondition);
}
