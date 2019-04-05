package lesson11.touragency.country.service;


import lesson11.touragency.common.business.exception.UncheckedException;
import lesson11.touragency.common.solution.BaseService;
import lesson11.touragency.country.domain.Country;
import lesson11.touragency.country.search.CountrySearchCondition;

import java.util.List;

public interface CountryService extends BaseService<Country, Long> {
    List<Country> search(CountrySearchCondition searchCondition);

    void removeAllCitiesFromCountry(Long countryId) throws UncheckedException;
}
