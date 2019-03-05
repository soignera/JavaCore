package lesson10.touragency.city.repo;

import lesson10.touragency.city.domain.City;
import lesson10.touragency.city.search.CitySearchCondition;
import lesson10.touragency.common.business.repo.BaseRepo;

import java.util.List;

public interface CityRepo extends BaseRepo {
    void add(City city);
    City findById(long id);
    //City[] search(CitySearchCondition searchCondition);
    List<City> search(CitySearchCondition searchCondition);
    void update(City city);
}
