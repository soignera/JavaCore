package lesson24.touragency.common.business.application;//package lesson8.touragency.common.business.application;


import lesson22.touragency.city.repo.CityRepo;
import lesson22.touragency.city.service.CityDefaultService;
import lesson22.touragency.city.service.CityService;
import lesson22.touragency.common.business.application.ServiceFactory;
import lesson22.touragency.country.service.CountryDefaultService;
import lesson22.touragency.country.service.CountryService;
import lesson22.touragency.order.repo.OrderRepo;
import lesson22.touragency.order.service.OrderDefaultService;
import lesson22.touragency.order.service.OrderService;
import lesson22.touragency.user.repo.UserRepo;
import lesson22.touragency.user.service.UserDefaultService;
import lesson22.touragency.user.service.UserService;
import lesson22.touragency.country.repo.CountryRepo;

public class MemoryArrayServiceFactory implements ServiceFactory {
    private OrderRepo orderRepo = new OrderArrayRepo();
    private CityRepo cityRepo = new CityArrayRepo();
    private CountryRepo countryRepo = new CountryArrayRepo();
    private UserRepo userRepo = new UserArrayRepo();

    private CityService cityService = new CityDefaultService(cityRepo, orderRepo);
    private OrderService orderService = new OrderDefaultService(orderRepo, countryRepo, cityRepo, userRepo);
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
