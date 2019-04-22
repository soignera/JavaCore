package lesson24.touragency.city.service;


import lesson24.touragency.city.domain.City;
import lesson24.touragency.city.exception.unchecked.DeleteCityException;
import lesson24.touragency.city.repo.CityRepo;
import lesson24.touragency.city.search.CitySearchCondition;
import lesson24.touragency.common.business.exception.UncheckedException;
import lesson24.touragency.order.repo.OrderRepo;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static lesson24.touragency.city.exception.CityExceptionMeta.DELETE_CITY_CONSTRAINT_ERROR;


public class CityDefaultService implements CityService {
    private final CityRepo cityRepo;
    //private static CityService cityServiceInstance;
    private final OrderRepo orderRepo;



    public CityDefaultService(CityRepo cityRepo, OrderRepo orderRepo) {
        this.cityRepo = cityRepo;
        this.orderRepo = orderRepo;
    }
//    public CityDefaultService(){};
//    public static CityService getServiceInstance() {
//        if (cityServiceInstance == null) {
//            cityServiceInstance = new CityDefaultService();
//        }
//        return cityServiceInstance;
//    }
@Override
public void add(Collection<City> cities) {
    if (cities != null && !cities.isEmpty()) {
        cityRepo.add(cities);
    }
}

    @Override
    public City add(City city) {
        if (city != null) {
            cityRepo.add(city);
        }

        return city;
    }



    @Override
    public Optional<City> findById(Long id) {
        if (id != null) {
            return cityRepo.findById(id);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void delete(City city) {
        if (city.getId() != null) {
            this.deleteById(city.getId());
        }
    }

    @Override
    public void deleteById(Long id) throws UncheckedException {
        if (id != null) {

            boolean noOrders = orderRepo.countByCity(id) == 0;
            if (noOrders) {
                cityRepo.deleteById(id);
            } else {
                throw new DeleteCityException(DELETE_CITY_CONSTRAINT_ERROR);
            }

        }
    }
    @Override
    public List<? extends City> search(CitySearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return cityRepo.findById(searchCondition.getId()).map(Collections::singletonList).orElse(Collections.emptyList());
        } else {
            return cityRepo.search(searchCondition);
        }

    }

    @Override
    public void printAll() {
        cityRepo.printAll();
    }
    @Override
    public void update(City city) {
        if (city.getId() != null) {
            cityRepo.update(city);
        }
    }

    @Override
    public List<City> findAll() {
        return cityRepo.findAll();
    }
    @Override
    public int countAll() {
        return cityRepo.countAll();
    }
    @Override
    public List<City> getCitiesByCountryId(Long countryId) {
        if (countryId != null) {
            return cityRepo.getCitiesByCountryId(countryId);
        }
        return Collections.emptyList();
    }
}
