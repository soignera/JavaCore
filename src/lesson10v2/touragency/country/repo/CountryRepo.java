package lesson10v2.touragency.country.repo;

import lesson10v2.touragency.common.business.repo.BaseRepo;
import lesson10v2.touragency.country.domain.Country;
import lesson10v2.touragency.country.search.CountrySearchCondition;

public interface CountryRepo extends BaseRepo {
    void add(Country country);
    Country findById(long id);
    Country[] search(CountrySearchCondition searchCondition);
}
