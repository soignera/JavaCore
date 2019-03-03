package lesson8.touragency.city.repo;

import lesson8.touragency.city.domain.City;
import lesson8.touragency.city.search.CitySearchCondition;
import lesson8.touragency.common.business.repo.BaseRepo;

import java.util.List;

public interface CityRepo extends BaseRepo {
    void add(City city);
    City findById(long id);
    //City[] search(CitySearchCondition searchCondition);
    List<City> search(CitySearchCondition searchCondition);
    void update(City city);
}
