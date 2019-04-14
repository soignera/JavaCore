package lesson24.touragency.common.business.application;

import lesson22.touragency.city.service.CityService;
import lesson22.touragency.country.service.CountryService;
import lesson22.touragency.order.service.OrderService;
import lesson22.touragency.user.service.UserService;

public interface ServiceFactory {
    CityService getCityService();
    CountryService getCountryService();
    OrderService getOrderService();
    UserService getUserService();

}
