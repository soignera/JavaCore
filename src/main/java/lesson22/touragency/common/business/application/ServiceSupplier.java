package lesson22.touragency.common.business.application;

import lesson17.touragency.city.service.CityService;
import lesson17.touragency.common.business.application.MemoryCollectionServiceFactory;
import lesson17.touragency.common.business.application.ServiceFactory;
import lesson17.touragency.common.business.application.StorageType;
import lesson17.touragency.country.service.CountryService;
import lesson17.touragency.order.service.OrderService;
import lesson17.touragency.user.service.UserService;

public final class ServiceSupplier {
    private static volatile ServiceSupplier INSTANCE;
    private ServiceFactory serviceFactory;

    public static ServiceSupplier getInstance() {
        return INSTANCE;
    }

    public static ServiceSupplier newInstance(lesson17.touragency.common.business.application.StorageType storageType) {

        if (INSTANCE == null) {
            synchronized (ServiceSupplier.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ServiceSupplier(storageType);
                }
            }
        }
        return INSTANCE;
    }

    private ServiceSupplier(lesson17.touragency.common.business.application.StorageType storageType) {
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
