package lesson10.touragency.country.service;

import lesson10.touragency.common.business.service.BaseService;
import lesson10.touragency.country.domain.Country;
import lesson10.touragency.country.search.CountrySearchCondition;

public interface CountryService extends BaseService {
    void add(Country country);

    Country findById(Long id);
    void delete(Country country);

    Country[] search(CountrySearchCondition searchCondition);
}
