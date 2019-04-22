package lesson24.touragency.common.business.application;

import lesson24.touragency.city.service.CityService;
import lesson24.touragency.country.service.CountryService;
import lesson24.touragency.order.service.OrderService;
import lesson24.touragency.user.service.UserService;

public interface ServiceFactory {
    CityService getCityService();
    CountryService getCountryService();
    OrderService getOrderService();
    UserService getUserService();

}
