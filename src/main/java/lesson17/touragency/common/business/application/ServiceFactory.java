package lesson17.touragency.common.business.application;

import lesson17.touragency.city.service.CityService;
import lesson17.touragency.country.service.CountryService;
import lesson17.touragency.order.service.OrderService;
import lesson17.touragency.user.service.UserService;

public interface ServiceFactory {
    CityService getCityService();
    CountryService getCountryService();
    OrderService getOrderService();
    UserService getUserService();

}
