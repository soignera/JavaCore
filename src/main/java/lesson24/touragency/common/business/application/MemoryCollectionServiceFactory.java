package lesson24.touragency.common.business.application;

import lesson24.touragency.city.repo.CityCollectionRepo;
import lesson24.touragency.city.repo.CityRepo;
import lesson24.touragency.city.service.CityDefaultService;
import lesson24.touragency.city.service.CityService;
import lesson24.touragency.country.repo.CountryCollectionRepo;
import lesson24.touragency.country.repo.CountryRepo;
import lesson24.touragency.country.service.CountryDefaultService;
import lesson24.touragency.country.service.CountryService;
import lesson24.touragency.order.repo.OrderCollectionRepo;
import lesson24.touragency.order.repo.OrderRepo;
import lesson24.touragency.order.service.OrderMemoryService;
import lesson24.touragency.order.service.OrderService;
import lesson24.touragency.user.repo.UserCollectionRepo;
import lesson24.touragency.user.repo.UserRepo;
import lesson24.touragency.user.service.UserDefaultService;
import lesson24.touragency.user.service.UserService;

public class MemoryCollectionServiceFactory implements ServiceFactory {
    private OrderRepo orderRepo = new OrderCollectionRepo();
    private CityRepo cityRepo = new CityCollectionRepo();
    private CountryRepo countryRepo = new CountryCollectionRepo();
    private UserRepo userRepo = new UserCollectionRepo();

    private CityService cityService = new CityDefaultService(cityRepo, orderRepo);
    private OrderService orderService = new OrderMemoryService(orderRepo, countryRepo, cityRepo, userRepo);
    private UserService userService = new UserDefaultService(userRepo, orderService);
    private CountryService countryService = new CountryDefaultService(countryRepo, cityService, orderRepo);

    @Override
    public CountryService getCountryService() {
        return countryService;
    }

    @Override
    public CityService getCityService() {
        return cityService;
    }

    @Override
    public OrderService getOrderService() {
        return orderService;
    }

    @Override
    public UserService getUserService() {
        return userService;
    }

}