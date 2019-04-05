package lesson8.touragency.common.business.application;

import lesson8.touragency.city.service.CityService;

public interface ServiceFactory {
    CityService getCityService();

}
