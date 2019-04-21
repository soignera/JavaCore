package lesson24.touragency.common.business.application;


import lesson24.touragency.city.service.CityService;
import lesson24.touragency.country.service.CountryService;
import lesson24.touragency.order.service.OrderService;
import lesson24.touragency.user.service.UserService;

public final class ServiceSupplier {
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
            case MEMORY_ARRAY: {
                serviceFactory = new MemoryArrayServiceFactory();
                break;
            }

            case RELATIONAL_DB: {
                serviceFactory = new RelationalDbServiceFactory();
                break;
            }

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
