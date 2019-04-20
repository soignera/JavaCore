package lesson24.touragency.country.service;//package lesson10v2.touragency.country.service;


import lesson24.touragency.city.domain.City;
import lesson24.touragency.city.repo.CityRepo;
import lesson24.touragency.city.service.CityService;
import lesson24.touragency.common.business.exception.UncheckedException;
import lesson24.touragency.country.domain.Country;
import lesson24.touragency.country.exception.DeleteCountryException;
import lesson24.touragency.country.repo.CountryRepo;
import lesson24.touragency.country.search.CountrySearchCondition;
import lesson24.touragency.country.service.CountryService;
import lesson24.touragency.order.repo.OrderRepo;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static lesson24.touragency.country.exception.CountryExceptionMeta.DELETE_COUNTRY_CONSTRAINT_ERROR;

public class CountryDefaultService implements CountryService {
    private final CountryRepo countryRepo;
    private final CityRepo cityRepo;
    private final CityService cityService;
    private final OrderRepo orderRepo;

    public CountryDefaultService(CountryRepo countryRepo, CityRepo cityRepo, CityService cityService, OrderRepo orderRepo) {
        this.countryRepo = countryRepo;
        this.cityRepo = cityRepo;
        this.cityService = cityService;
        this.orderRepo = orderRepo;
    }
    @Override
    public void add(Collection<Country> countries) {
        if (countries != null && !countries.isEmpty()) {
            for (Country country : countries) {
                countryRepo.add(country);

                if (country.getCities() != null && !country.getCities().isEmpty()) {
                    country.getCities().replaceAll(city -> {
                        city.setCountryId(country.getId());
                        return city;
                    });
                    cityService.add(country.getCities());
                }
            }
        }
    }

    @Override
    public Country add(Country country) {
        if (country != null) {
            countryRepo.add(country);

            if (country.getCities() != null) {
                for (City city : country.getCities()) {
                    if (city != null) {
                        cityRepo.add(city);
                    }
                }
            }
        }
        return country;
    }

    @Override
    public Country findById(Long id) {
        if (id != null) {
            return countryRepo.findById(id);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Country country) {
        if (country.getId() != null) {
            this.deleteById(country.getId());
        }
    }

    @Override
    public void deleteById(Long id) {
        if (id != null) {
            boolean noOrders = orderRepo.countByCountry(id) == 0;

            if (noOrders) {
                removeAllCitiesFromCountry(id);
                countryRepo.deleteById(id);
            } else {
                throw new DeleteCountryException(DELETE_COUNTRY_CONSTRAINT_ERROR);
            }
        }
    }

    @Override
    public void printAll() {
        countryRepo.printAll();
    }


    @Override
    public List<Country> search(CountrySearchCondition searchCondition) {
        return countryRepo.search(searchCondition);
    }
    @Override
    public void removeAllCitiesFromCountry(Long countryId) throws UncheckedException {
       Country country = findById(countryId);
        if (country != null) {
            List<City> cities = country.getCities() == null ? Collections.emptyList() : country.getCities();

            for (City city : cities) {
                cityService.deleteById(city.getId());
            }

        }
    }

    @Override
    public void update(Country country) {
        if (country.getId() != null) {
            countryRepo.update(country);
        }
    }
    @Override
    public List<Country> findAll() {
        return countryRepo.findAll();
    }
    @Override
    public int countAll() {
        return countryRepo.countAll();
    }
    @Override
    public List<Country> findAllCountriesFetchingCities() {
        return countryRepo.findAllCountriesFetchingCities();
    }
}

