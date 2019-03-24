package lesson11.touragency.country.repo;

import lesson11.touragency.country.search.CountrySearchCondition;
import lesson11.touragency.storage.AtomicSequenceGenerator;
import lesson11.touragency.country.domain.Country;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static lesson11.touragency.storage.Storage.countriesList;

public class CountryCollectionRepo implements CountryRepo {
    private CountryOrderingComponent orderingComponent = new CountryOrderingComponent();

    @Override
    public void add(Country country) {
        country.setId(AtomicSequenceGenerator.getNextValue());
        countriesList.add(country);
    }

    @Override
    public Country findById(Long id) {
        return findCountryById(id);
    }

    @Override
    public void update(Country country) {
        //we already in memory, no need to update object
    }

    @Override
    public List<Country> search(CountrySearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return Collections.singletonList(findById(searchCondition.getId()));
        } else {
            List<Country> result = doSearch(searchCondition);

            boolean needOrdering = !result.isEmpty() && searchCondition.needOrdering();
            if (needOrdering) {
                orderingComponent.applyOrdering(result, searchCondition);
            }

            return result;
        }
    }

    private List<Country> doSearch(CountrySearchCondition searchCondition) {
        List<Country> result = new ArrayList<>();
        for (Country country : countriesList) {
            if (country != null) {
                boolean found = true;

                if (searchCondition.searchByLanguag()) {
                    found = searchCondition.getLanguag().equals(country.getLanguag());
                }

                if (found && searchCondition.searchByName()) {
                    found = searchCondition.getName().equals(country.getName());
                }

                if (found) {
                    result.add(country);
                }
            }
        }
        return result;
    }


    @Override
    public void deleteById(Long id) {
        Country found = findCountryById(id);

        if (found != null) {
            countriesList.remove(found);
        }
    }

    @Override
    public void printAll() {
        for (Country country : countriesList) {
            System.out.println(country);
        }
    }

    private Country findCountryById(long countryId) {
        for (Country country : countriesList) {
            if (Long.valueOf(countryId).equals(country.getId())) {
                return country;
            }
        }
        return null;
    }

    @Override
    public List<Country> findAll() {
        return countriesList;
    }
}
