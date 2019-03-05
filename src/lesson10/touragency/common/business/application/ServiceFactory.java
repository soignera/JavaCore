package lesson10.touragency.common.business.application;

import lesson10.touragency.city.service.CityService;

public interface ServiceFactory {
    CityService getCityService();

}
