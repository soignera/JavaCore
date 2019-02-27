package lesson7.touragency.city.repo;

import touragency.city.domain.City;
import touragency.city.search.CitySearchCondition;
import touragency.common.business.repo.BaseRepo;

public interface CityRepo extends BaseRepo {
    void add(City city);
    City findById(long id);
    City[] search(CitySearchCondition searchCondition);
}
