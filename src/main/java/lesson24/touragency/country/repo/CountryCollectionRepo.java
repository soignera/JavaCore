package lesson24.touragency.country.repo;

import lesson24.touragency.common.business.search.Paginator;
import lesson24.touragency.common.solution.utils.CollectionUtils;
import lesson24.touragency.country.domain.Country;
import lesson24.touragency.country.search.CountrySearchCondition;
import lesson24.touragency.storage.AtomicSequenceGenerator;
import lesson24.touragency.storage.Storage;

import java.util.*;

import static lesson24.touragency.storage.Storage.countriesList;

public class CountryCollectionRepo implements CountryRepo {
    private CountryOrderingComponent orderingComponent = new CountryOrderingComponent();

    @Override
    public Country add(Country country) {
        country.setId(AtomicSequenceGenerator.getNextValue());
        countriesList.add(country);

        return country;
    }
    @Override
    public List<Country> findAllCountriesFetchingCities() {
        return countriesList;
    }
    @Override
    public void add(Collection<Country> countries) {
        countries.forEach(this::add);

    }

    @Override
    public Optional<Country> findById(Long id) {
        return findCountryById(id);
    }

    @Override
    public void update(Country country) {
        //we already in memory, no need to update object
    }

    @Override
    public List<Country> search(CountrySearchCondition searchCondition) {
        List<Country> result = doSearch(searchCondition);

        boolean needOrdering = !result.isEmpty() && searchCondition.needOrdering();
        if (needOrdering) {
            orderingComponent.applyOrdering(result, searchCondition);
        }

        if (!result.isEmpty() && searchCondition.shouldPaginate()) {
            result = getPageableData(result, searchCondition.getPaginator());
        }

        return result;
    }

    private List<Country> doSearch(CountrySearchCondition searchCondition) {
        List<Country> result = new ArrayList<>();
        List<Country> countriesList = Storage.countriesList;
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

    private List<Country> getPageableData(List<Country> countries, Paginator paginator) {
        return CollectionUtils.getPageableData(countries, paginator.getLimit(), paginator.getOffset());
    }
    @Override
    public void deleteById(Long id) {
        Optional<Country> foundOptional = findCountryById(id);
        foundOptional.map(country -> countriesList.remove(country));
    }

    @Override
    public void printAll() {
        countriesList.forEach(System.out::println);

    }

    private Optional<Country> findCountryById(long countryId) {
        return countriesList.stream().filter(country -> Long.valueOf(countryId).equals(country.getId())).findAny();

    }

    @Override
    public List<Country> findAll() {
        return countriesList;
    }
    @Override
    public int countAll() {
        return countriesList.size();
    }}
