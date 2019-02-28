package lesson8.touragency.country.service;

import lesson8.touragency.common.business.service.BaseService;
import lesson8.touragency.country.domain.Country;
import lesson8.touragency.country.search.CountrySearchCondition;

public interface CountryService extends BaseService {
    void add(Country country);

    Country findById(Long id);
    void delete(Country country);

    Country[] search(CountrySearchCondition searchCondition);
}
