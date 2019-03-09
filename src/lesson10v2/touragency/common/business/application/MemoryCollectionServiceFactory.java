package lesson10v2.touragency.common.business.application;

import lesson10v2.touragency.city.repo.CityMemoryCollectionRepo;
import lesson10v2.touragency.city.service.CityDefaultService;
import lesson10v2.touragency.city.service.CityService;

public class MemoryCollectionServiceFactory implements ServiceFactory {
    @Override
    public CityService getCityService() {
        return new CityDefaultService(new CityMemoryCollectionRepo());
    }

}
