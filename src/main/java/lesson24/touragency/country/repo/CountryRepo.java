package lesson24.touragency.country.repo;


import lesson24.touragency.common.solution.repo.BaseRepo;
import lesson24.touragency.country.domain.Country;
import lesson24.touragency.country.search.CountrySearchCondition;

import java.util.List;

public interface CountryRepo  extends BaseRepo<Country, Long> {

    List<Country> search(CountrySearchCondition searchCondition);
    List<Country> findAllCountriesFetchingCities();
}
