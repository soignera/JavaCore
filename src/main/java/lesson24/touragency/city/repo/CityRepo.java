package lesson24.touragency.city.repo;

import lesson24.touragency.city.domain.City;
import lesson24.touragency.city.search.CitySearchCondition;
import lesson24.touragency.common.solution.repo.BaseRepo;

import java.util.List;

public interface CityRepo extends BaseRepo<City, Long> {

    //City[] search(CitySearchCondition searchCondition);
    List<? extends City> search(CitySearchCondition searchCondition);
    List<City> getCitiesByCountryId(long countryId);


}
