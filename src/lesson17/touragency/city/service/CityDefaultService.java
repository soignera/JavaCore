package lesson17.touragency.city.service;



import lesson17.touragency.city.domain.City;
import lesson17.touragency.city.repo.CityRepo;
import lesson17.touragency.city.search.CitySearchCondition;
import lesson17.touragency.order.repo.OrderRepo;

import java.util.List;


public class CityDefaultService implements CityService {
    private CityRepo cityRepo;
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
    public void add(City city) {
        if (city != null) {
            cityRepo.add(city);
        }
    }

    @Override
    public City findById(Long id) {
        if (id != null) {
            return cityRepo.findById(id);
        } else {
            return null;
        }
    }

    @Override
    public void delete(City city) {
        if (city.getId() != null) {
            this.deleteById(city.getId());
        }
    }

    @Override
    public void deleteById(Long id) {
        if (id != null) {
            cityRepo.deleteById(id);
        }
    }
    @Override
    public List<? extends City> search(CitySearchCondition searchCondition) {
        return cityRepo.search(searchCondition);

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

}
