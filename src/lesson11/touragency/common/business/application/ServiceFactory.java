package lesson11.touragency.common.business.application;

import lesson11.touragency.city.service.CityService;
import lesson11.touragency.country.domain.Country;
import lesson11.touragency.country.service.CountryService;
import lesson11.touragency.order.service.OrderService;
import lesson11.touragency.user.service.UserService;

public interface ServiceFactory {
    CityService getCityService();
    CountryService getCountryService();
    OrderService getOrderService();
    UserService getUserService();

}
