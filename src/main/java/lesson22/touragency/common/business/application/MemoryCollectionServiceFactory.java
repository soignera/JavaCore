package lesson22.touragency.common.business.application;

import lesson17.touragency.city.repo.CityMemoryCollectionRepo;
import lesson17.touragency.city.repo.CityRepo;
import lesson17.touragency.city.service.CityDefaultService;
import lesson17.touragency.city.service.CityService;
import lesson17.touragency.common.business.application.ServiceFactory;
import lesson17.touragency.country.repo.CountryCollectionRepo;
import lesson17.touragency.country.repo.CountryRepo;
import lesson17.touragency.country.service.CountryDefaultService;
import lesson17.touragency.country.service.CountryService;
import lesson17.touragency.order.repo.OrderCollectionRepo;
import lesson17.touragency.order.repo.OrderRepo;
import lesson17.touragency.order.service.OrderDefaultService;
import lesson17.touragency.order.service.OrderService;
import lesson17.touragency.user.repo.UserCollectionRepo;
import lesson17.touragency.user.repo.UserRepo;
import lesson17.touragency.user.service.UserDefaultService;
import lesson17.touragency.user.service.UserService;

public class MemoryCollectionServiceFactory implements ServiceFactory {
    private OrderRepo orderRepo = new OrderCollectionRepo();
    private CityRepo cityRepo = new CityMemoryCollectionRepo();
    private CountryRepo countryRepo = new CountryCollectionRepo();
    private UserRepo userRepo = new UserCollectionRepo();

    private CityService cityService = new CityDefaultService(cityRepo, orderRepo);
    private OrderService orderService = new OrderDefaultService(orderRepo);
    private UserService userService = new UserDefaultService(userRepo, orderService);
    private CountryService countryService = new CountryDefaultService(countryRepo, cityRepo, cityService, orderRepo);

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