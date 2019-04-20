package lesson24.touragency.country.service;


import lesson24.touragency.common.business.exception.UncheckedException;
import lesson24.touragency.common.solution.BaseService;
import lesson24.touragency.country.domain.Country;
import lesson24.touragency.country.search.CountrySearchCondition;

import java.util.List;

public interface CountryService extends BaseService<Country, Long> {
    List<Country> search(CountrySearchCondition searchCondition);

    void removeAllCitiesFromCountry(Long countryId) throws UncheckedException;
    List<Country> findAllCountriesFetchingCities();
}
