package touragency.country.service;

import touragency.city.City;
import touragency.city.search.CitySearchCondition;
import touragency.common.business.service.BaseService;
import touragency.country.Country;
import touragency.country.search.CountrySearchCondition;

public interface CountryService extends BaseService {
    void add(Country country);

    Country findById(Long id);
    void delete(Country country);

    Country[] search(CountrySearchCondition searchCondition);
}
