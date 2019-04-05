package lesson10v2.touragency.country.repo;


import lesson10v2.touragency.common.solution.BaseRepo;
import lesson10v2.touragency.country.domain.Country;
import lesson10v2.touragency.country.search.CountrySearchCondition;

public interface CountryRepo extends BaseRepo {
    void add(Country country);
    Country findById(Long id);
    Country[] search(CountrySearchCondition searchCondition);
}
