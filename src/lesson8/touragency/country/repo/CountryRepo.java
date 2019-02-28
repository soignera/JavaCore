package lesson8.touragency.country.repo;

import lesson8.touragency.common.business.repo.BaseRepo;
import lesson8.touragency.country.domain.Country;
import lesson8.touragency.country.search.CountrySearchCondition;

public interface CountryRepo extends BaseRepo {
    void add(Country country);
    Country findById(long id);
    Country[] search(CountrySearchCondition searchCondition);
}
