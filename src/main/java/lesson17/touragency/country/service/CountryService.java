package lesson17.touragency.country.service;


import lesson17.touragency.common.business.exception.UncheckedException;
import lesson17.touragency.common.solution.BaseService;
import lesson17.touragency.country.domain.Country;
import lesson17.touragency.country.search.CountrySearchCondition;

import java.util.List;

public interface CountryService extends BaseService<Country, Long> {
    List<Country> search(CountrySearchCondition searchCondition);

    void removeAllCitiesFromCountry(Long countryId) throws UncheckedException;
}
