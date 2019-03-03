package lesson8.touragency.common.business.application;

import lesson8.touragency.city.domain.City;
import lesson8.touragency.city.repo.CityMemoryCollectionRepo;
import lesson8.touragency.city.service.CityDefaultService;
import lesson8.touragency.city.service.CityService;
import lesson8.touragency.common.solution.ArrayUtils;

import static lesson8.touragency.storage.Storage.cities;

public class MemoryCollectionServiceFactory implements ServiceFactory {
    @Override
    public CityService getCityService() {
        return new CityDefaultService(new CityMemoryCollectionRepo());
    }

}
