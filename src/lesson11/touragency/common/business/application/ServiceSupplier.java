package lesson11.touragency.common.business.application;

import lesson11.touragency.city.service.CityService;
import lesson11.touragency.country.service.CountryService;
import lesson11.touragency.order.service.OrderService;
import lesson11.touragency.user.service.UserService;
import touragency.country.domain.Country;

public class ServiceSupplier {
    private static volatile ServiceSupplier INSTANCE;
    private ServiceFactory serviceFactory;

    public static ServiceSupplier getInstance() {
        return INSTANCE;
    }

    public static ServiceSupplier newInstance(StorageType storageType) {

        if (INSTANCE == null) {
            synchronized (ServiceSupplier.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ServiceSupplier(storageType);
                }
            }
        }
        return INSTANCE;
    }

    private ServiceSupplier(StorageType storageType) {
        initServiceFactory(storageType);
    }

    private void initServiceFactory(StorageType storageType) {
        switch (storageType) {
//            case MEMORY_ARRAY: {
//                serviceFactory = new MemoryArrayServiceFactory();
//            }
            default: {
                serviceFactory = new MemoryCollectionServiceFactory();
            }

        }
    }

    public CityService getCityService() {
        return serviceFactory.getCityService();
    }
    public CountryService getCountryService() {
        return serviceFactory.getCountryService();
    }
    public OrderService getOrderService() {
        return serviceFactory.getOrderService();
    }
    public UserService getUserService() {
        return serviceFactory.getUserService();
    }

}
