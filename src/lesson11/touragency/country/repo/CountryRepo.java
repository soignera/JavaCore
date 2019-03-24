package lesson11.touragency.country.repo;


import lesson11.touragency.city.domain.City;
import lesson11.touragency.common.solution.BaseRepo;
import lesson11.touragency.country.domain.Country;
import lesson11.touragency.country.search.CountrySearchCondition;

import java.util.List;

public interface CountryRepo  extends BaseRepo<Country, Long> {
    void add(Country country);
    void update(Country country);
    Country findById(Long id);
    List<Country> search(CountrySearchCondition searchCondition);
}
