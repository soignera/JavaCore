package lesson10v2.touragency.country.service;

import lesson10v2.touragency.common.business.service.BaseService;
import lesson10v2.touragency.country.domain.Country;
import lesson10v2.touragency.country.search.CountrySearchCondition;

public interface CountryService extends BaseService {
    void add(Country country);

    Country findById(Long id);
    void delete(Country country);

    Country[] search(CountrySearchCondition searchCondition);
}
