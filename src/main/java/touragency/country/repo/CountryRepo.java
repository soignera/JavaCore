package touragency.country.repo;

import touragency.common.business.repo.BaseRepo;
import touragency.country.domain.Country;
import touragency.country.search.CountrySearchCondition;

public interface CountryRepo extends BaseRepo {
    void add(Country country);
    Country findById(long id);
    Country[] search(CountrySearchCondition searchCondition);
}
