package lesson17.touragency.city.repo;

import lesson17.touragency.city.domain.City;
import lesson17.touragency.city.search.CitySearchCondition;
import lesson17.touragency.common.solution.BaseRepo;

import java.util.List;

public interface CityRepo extends BaseRepo<City, Long> {
    void add(City city);
    City findById(Long id);
    //City[] search(CitySearchCondition searchCondition);
    List<City> search(CitySearchCondition searchCondition);
    void update(City city);
}
