package touragency.city.service;

import touragency.city.domain.City;

import touragency.city.search.CitySearchCondition;
import touragency.common.business.service.BaseService;

import java.util.List;

public interface CityService extends BaseService {
    void add(City city);

    City findById(Long id);
    void delete(City city);

    City[] search(CitySearchCondition searchCondition);
}
