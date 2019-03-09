package lesson10v2.touragency;

import lesson10v2.touragency.city.domain.City;
import lesson10v2.touragency.city.domain.Climate;
import lesson10v2.touragency.city.search.CityOrderByField;
import lesson10v2.touragency.city.search.CitySearchCondition;
import lesson10v2.touragency.city.service.CityService;
import lesson10v2.touragency.common.business.application.ServiceSupplier;
import lesson10v2.touragency.common.business.application.StorageType;
import lesson10v2.touragency.common.business.search.OrderDirection;
import lesson10v2.touragency.common.business.search.OrderType;

import java.util.List;

public class TourAgencyDemo2 {
    private static class Application {
        static {
            ServiceSupplier.newInstance(StorageType.MEMORY_COLLECTION);
        }
        private CityService cityService = ServiceSupplier.getInstance().getCityService();
       //private CityService cityService = CityDefaultService.getServiceInstance();




        private void addCities() {
            //List<City> listCitites= new ArrayList<>();
            cityService.add(new City("123", Climate.HUMID_CONTINENTAL,1,true));
            cityService.add(new City("123", Climate.HUMID_CONTINENTAL,1,true));
            cityService.add(new City("123", Climate.HUMID_CONTINENTAL,1,true));
            cityService.add(new City("1234", Climate.HUMID_CONTINENTAL,1,true));

        }
//        private void searchCities() {
//            CitySearchCondition citySearchCondition = new CitySearchCondition();
//            citySearchCondition.setOrderType(OrderType.DESC);
//            List<City> searchResult = cityService.search(citySearchCondition);
//            for (City city : searchResult) {
//                System.out.println(city);
//            }
//
//        }
//        private void searchCities2() {
//            CitySearchCondition citySearchCondition = new CitySearchCondition();
//            citySearchCondition.setName("123");
//            citySearchCondition.setOrderType(OrderType.ASC);
//            List<City> searchResult = cityService.search(citySearchCondition);
//            for (City city : searchResult) {
//                System.out.println(city);
//            }
//
//        }
        public void searchCitiesWithoutOrder() {
            System.out.println("\n----------Search marks No order ------------");
            CitySearchCondition citySearchCondition = new CitySearchCondition();
            List<City> searchResult = cityService.search(citySearchCondition);
            for (City city : searchResult) {
                System.out.println(city);
            }
        }

        public void searchCitiesWithOrderAsc() {
            System.out.println("\n----------Search marks Order ASC ------------");
            CitySearchCondition citySearchCondition = new CitySearchCondition();
            citySearchCondition.setOrderDirection(OrderDirection.ASC);
            citySearchCondition.setOrderByField(CityOrderByField.CLIMATE);
            List<City> searchResult = cityService.search(citySearchCondition);
            for (City city: searchResult) {
                System.out.println(city);
            }
        }

        public void searchCitiesWithOrderDesc() {
            System.out.println("\n----------Search marks Order Desc ------------");
            CitySearchCondition citySearchCondition = new CitySearchCondition();
            citySearchCondition.setOrderDirection(OrderDirection.DESC);
            citySearchCondition.setOrderByField(CityOrderByField.CLIMATE);
            List<City> searchResult = cityService.search(citySearchCondition);
            for (City city : searchResult) {
                System.out.println(city);
            }
        }

        public void searchCitiesWithComplexOrderAsc() {
            System.out.println("\n----------Search marks COMPLEX Order Desc ------------");
            CitySearchCondition citySearchCondition = new CitySearchCondition();
            citySearchCondition.setOrderDirection(OrderDirection.ASC);
            citySearchCondition.setOrderByField(CityOrderByField.CLIMATE);
            citySearchCondition.setOrderType(OrderType.COMPLEX);
            List<City> searchResult = cityService.search(citySearchCondition);
            for (City city : searchResult) {
                System.out.println(city);
            }
        }

        public void searchCitiesWithComplexOrderDesc() {
            System.out.println("\n----------Search marks COMPLEX Order Desc ------------");
            CitySearchCondition citySearchCondition = new CitySearchCondition();
            citySearchCondition.setOrderDirection(OrderDirection.DESC);
            citySearchCondition.setOrderByField(CityOrderByField.CLIMATE);
            citySearchCondition.setOrderType(OrderType.COMPLEX);
            List<City> searchResult = cityService.search(citySearchCondition);
            for (City city : searchResult) {
                System.out.println(city);
            }
        }




        public void fillStorage() {
            addCities();

        }
        public void printCities(){cityService.printAll();
        }

    }
    public static void main(String[] args) {
        Application application = new Application();
        application.fillStorage();
        System.out.println("--------Cities------------");
        application.printCities();
//        application.searchCities();
//        application.searchCities2();
        application.searchCitiesWithoutOrder();
        application.searchCitiesWithOrderAsc();
        application.searchCitiesWithOrderDesc();
        application.searchCitiesWithComplexOrderAsc();
        application.searchCitiesWithComplexOrderDesc();

    }


}
