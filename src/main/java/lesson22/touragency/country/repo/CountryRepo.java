package lesson22.touragency.country.repo;


import lesson22.touragency.common.solution.repo.BaseRepo;
import lesson22.touragency.country.domain.Country;
import lesson22.touragency.country.search.CountrySearchCondition;

import java.util.List;

public interface CountryRepo  extends BaseRepo<Country, Long> {

    List<Country> search(CountrySearchCondition searchCondition);
    List<Country> findAllCountriesFetchingModels();
}
