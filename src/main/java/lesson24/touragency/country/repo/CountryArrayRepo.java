package lesson24.touragency.country.repo;//package lesson10v2.touragency.country.repo;

import lesson24.touragency.common.business.search.Paginator;
import lesson24.touragency.common.solution.utils.ArrayUtils;
import lesson24.touragency.common.solution.utils.CollectionUtils;
import lesson24.touragency.common.solution.utils.OptionalUtils;
import lesson24.touragency.country.domain.Country;
import lesson24.touragency.country.search.CountrySearchCondition;
import lesson24.touragency.storage.AtomicSequenceGenerator;

import java.util.*;
import java.util.stream.IntStream;

import static lesson24.touragency.storage.Storage.countriesArray;

public class CountryArrayRepo implements CountryRepo {
    private CountryOrderingComponent orderingComponent = new CountryOrderingComponent();
    private int countryIndex = -1;

    @Override
    public Country add(Country country) {
        if (countryIndex == countriesArray.length - 1) {
            Country[] newArrCountries = new Country[countriesArray.length * 2];
            System.arraycopy(countriesArray, 0, newArrCountries, 0, countriesArray.length);
            countriesArray = newArrCountries;
        }

        countryIndex++;
        country.setId(AtomicSequenceGenerator.getNextValue());
        countriesArray[countryIndex] = country;

        return country;
    }

    @Override
    public void add(Collection<Country> countries) {
        countries.forEach(this::add);
    }

    @Override
    public void update(Country country) {
        //we already in memory, no need to update object
    }

    @Override
    public Optional<Country> findById(Long id) {
        return findCountryIndexById(id).map(countryIndex -> countriesArray[countryIndex]);
    }

    @Override
    public List<Country> search(CountrySearchCondition searchCondition) {
        List<Country> result = doSearch(searchCondition);
        boolean needOrdering = !result.isEmpty() && searchCondition.needOrdering();

        if (needOrdering) {
            orderingComponent.applyOrdering(result, searchCondition);
        }

        if (!result.isEmpty() && searchCondition.shouldPaginate()) {
            return getPageableData(result, searchCondition.getPaginator());
        }

        return result;
    }

    @Override
    public List<Country> findAllCountriesFetchingCities() {
        return new ArrayList<>(Arrays.asList(countriesArray));
    }

    private List<Country> doSearch(CountrySearchCondition searchCondition) {
        Country[] result = new Country[countriesArray.length];
        int resultIndex = 0;

        for (Country country : countriesArray) {
            if (country != null) {
                boolean found = true;

                if (searchCondition.searchByLanguag()) {
                    found = searchCondition.getLanguag().equals(country.getLanguag());
                }

                if (found && searchCondition.searchByName()) {
                    found = searchCondition.getName().equals(country.getName());
                }

                if (found) {
                    result[resultIndex] = country;
                    resultIndex++;
                }
            }
        }

        if (resultIndex > 0) {
            Country[] toReturn = new Country[resultIndex];
            System.arraycopy(result, 0, toReturn, 0, resultIndex);
            return new ArrayList<>(Arrays.asList(toReturn));
        }

        return Collections.emptyList();
    }

    private List<Country> getPageableData(List<Country> countries, Paginator paginator) {
        return CollectionUtils.getPageableData(countries, paginator.getLimit(), paginator.getOffset());
    }

    @Override
    public void deleteById(Long id) {
        findCountryIndexById(id).ifPresent(this::deleteCountryByIndex);
    }

    private void deleteCountryByIndex(int index) {
        ArrayUtils.removeElement(countriesArray, index);
        countryIndex--;
    }

    @Override
    public void printAll() {
        Arrays.stream(countriesArray).filter(Objects::nonNull).forEach(System.out::println);
    }

    private Optional<Integer> findCountryIndexById(long countryId) {
        OptionalInt optionalInt = IntStream.range(0, countriesArray.length).filter(i ->
                countriesArray[i] != null && Long.valueOf(countryId).equals(countriesArray[i].getId())
        ).findAny();

        return OptionalUtils.valueOf(optionalInt);
    }

    @Override
    public List<Country> findAll() {
        return new ArrayList<>(Arrays.asList(countriesArray));
    }

    @Override
    public int countAll() {
        return countriesArray.length;
    }
}