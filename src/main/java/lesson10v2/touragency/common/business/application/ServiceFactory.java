package lesson10v2.touragency.common.business.application;

import lesson10v2.touragency.city.service.CityService;

public interface ServiceFactory {
    CityService getCityService();

}
