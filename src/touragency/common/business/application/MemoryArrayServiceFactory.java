package touragency.common.business.application;

import touragency.city.repo.CityMemoryRepo;
import touragency.city.service.CityDefaultService;
import touragency.city.service.CityService;

public class MemoryArrayServiceFactory implements ServiceFactory {
    @Override
    public CityService getCityService() {
        return new CityDefaultService(new CityMemoryRepo());
    }
}
