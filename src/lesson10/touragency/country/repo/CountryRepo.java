package lesson10.touragency.country.repo;

import lesson10.touragency.common.business.repo.BaseRepo;
import lesson10.touragency.country.domain.Country;
import lesson10.touragency.country.search.CountrySearchCondition;

public interface CountryRepo extends BaseRepo {
    void add(Country country);
    Country findById(long id);
    Country[] search(CountrySearchCondition searchCondition);
}
