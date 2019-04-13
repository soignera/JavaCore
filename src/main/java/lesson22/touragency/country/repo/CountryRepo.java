package lesson22.touragency.country.repo;


import lesson17.touragency.common.solution.BaseRepo;
import lesson17.touragency.country.domain.Country;
import lesson17.touragency.country.search.CountrySearchCondition;

import java.util.List;

public interface CountryRepo  extends BaseRepo<Country, Long> {

    List<Country> search(CountrySearchCondition searchCondition);
}
