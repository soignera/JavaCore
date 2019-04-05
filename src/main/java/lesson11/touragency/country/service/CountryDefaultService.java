package lesson11.touragency.country.service;//package lesson10v2.touragency.country.service;


import lesson11.touragency.city.domain.City;
import lesson11.touragency.city.repo.CityRepo;
import lesson11.touragency.city.service.CityService;
import lesson11.touragency.common.business.exception.UncheckedException;
import lesson11.touragency.country.domain.Country;
import lesson11.touragency.country.exception.DeleteCountryException;
import lesson11.touragency.country.repo.CountryRepo;
import lesson11.touragency.country.search.CountrySearchCondition;
import lesson11.touragency.order.repo.OrderRepo;

import java.util.Collections;
import java.util.List;

import static lesson11.touragency.country.exception.CountryExceptionMeta.DELETE_COUNTRY_CONSTRAINT_ERROR;

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
    public void add(Country country) {
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
}

