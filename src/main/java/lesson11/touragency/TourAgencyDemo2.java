package lesson11.touragency;

import lesson11.touragency.city.domain.City;
import lesson11.touragency.city.search.CityOrderByField;
import lesson11.touragency.city.search.CitySearchCondition;
import lesson11.touragency.city.service.CityService;
import lesson11.touragency.common.business.application.ServiceSupplier;
import lesson11.touragency.common.business.application.StorageType;
import lesson11.touragency.common.business.exception.CheckedException;
import lesson11.touragency.common.business.search.OrderDirection;
import lesson11.touragency.common.business.search.OrderType;
import lesson11.touragency.common.solution.utils.FileUtils;
import lesson11.touragency.country.search.CountrySearchCondition;
import lesson11.touragency.country.service.CountryService;
import lesson11.touragency.country.domain.Country;
import lesson11.touragency.order.domain.Order;
import lesson11.touragency.reporting.ReportProvider;
import lesson11.touragency.storage.initor.StorageInitializer;
import lesson11.touragency.storage.initor.StorageInitorConstants;
import lesson11.touragency.user.domain.User;
import lesson11.touragency.user.service.UserService;
import lesson11.touragency.order.service.OrderService;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static lesson11.touragency.common.solution.utils.RandomUtils.getRandomInt;

public class TourAgencyDemo2 {
    private static class Application {
        static {
            ServiceSupplier.newInstance(StorageType.MEMORY_COLLECTION);
        }
        private CityService cityService = ServiceSupplier.getInstance().getCityService();
        private CountryService countryService = ServiceSupplier.getInstance().getCountryService();
        private UserService userService = ServiceSupplier.getInstance().getUserService();
        private OrderService orderService = ServiceSupplier.getInstance().getOrderService();

        //private CityService cityService = CityDefaultService.getServiceInstance();




        private void addCountries() {
            //List<City> listCitites= new ArrayList<>();
            countryService.add(new Country("123","123"));
            countryService.add(new Country("123","123"));
            countryService.add(new Country("123","123"));

        }
//        private void searchCities() {
//            CitySearchCondition citySearchCondition = new CitySearchCondition();
//            citySearchCondition.setOrderType(OrderType.DESC);
//            List<? extends City> searchResult = cityService.search(citySearchCondition);
//            for (City city : searchResult) {
//                System.out.println(city);
//            }
//
//        }
//        private void searchCities2() {
//            CitySearchCondition citySearchCondition = new CitySearchCondition();
//            citySearchCondition.setName("123");
//            citySearchCondition.setOrderType(OrderType.ASC);
//            List<? extends City> searchResult = cityService.search(citySearchCondition);
//            for (City city : searchResult) {
//                System.out.println(city);
//            }
//
//        }
        public void searchCountriesWithoutOrder() {
            System.out.println("\n----------Search countries No order ------------");
            CountrySearchCondition countrySearchCondition = new CountrySearchCondition();
            List<Country> searchResult = countryService.search(countrySearchCondition);
            for (Country country : searchResult) {
                System.out.println(country);
            }
        }
        public void searchCitiesWithoutOrder() {
            System.out.println("\n----------Search citiies No order ------------");
            CitySearchCondition citySearchCondition = new CitySearchCondition();
            List<? extends City> searchResult = cityService.search(citySearchCondition);
            for (City city : searchResult) {
                System.out.println(city);
            }
        }

        public void searchCitiesWithOrderAsc() {
            System.out.println("\n----------Search countries Order ASC ------------");
            CitySearchCondition citySearchCondition = new CitySearchCondition();
            citySearchCondition.setOrderDirection(OrderDirection.ASC);
            citySearchCondition.setOrderByField(CityOrderByField.CLIMATE);
            List<? extends City> searchResult = cityService.search(citySearchCondition);
            for (City city : searchResult) {
                System.out.println(city);
            }
            }


        public void searchCitiesWithOrderDesc() {
            System.out.println("\n----------Search countries Order Desc ------------");
            CitySearchCondition citySearchCondition = new CitySearchCondition();
            citySearchCondition.setOrderDirection(OrderDirection.DESC);
            citySearchCondition.setOrderByField(CityOrderByField.CLIMATE);
            List<? extends City> searchResult = cityService.search(citySearchCondition);
            for (City city : searchResult) {
                System.out.println(city);
            }
        }



        public void searchCitiesWithComplexOrderAsc() {
            System.out.println("\n----------Search countries COMPLEX Order Desc ------------");
            CitySearchCondition citySearchCondition = new CitySearchCondition();
            citySearchCondition.setOrderDirection(OrderDirection.ASC);
            citySearchCondition.setOrderByField(CityOrderByField.CLIMATE);
            citySearchCondition.setOrderType(OrderType.COMPLEX);
            List<? extends City> searchResult = cityService.search(citySearchCondition);
            for (City city : searchResult) {
                System.out.println(city);
            }
        }

        public void searchCitiesWithComplexOrderDesc() {
            System.out.println("\n----------Search countries COMPLEX Order Desc ------------");
            CitySearchCondition citySearchCondition = new CitySearchCondition();
            citySearchCondition.setOrderDirection(OrderDirection.DESC);
            citySearchCondition.setOrderByField(CityOrderByField.CLIMATE);
            citySearchCondition.setOrderType(OrderType.COMPLEX);
            List<? extends City> searchResult = cityService.search(citySearchCondition);
            for (City city : searchResult) {
                System.out.println(city);
            }
        }




        public void fillStorage() throws Exception {
            addCountries();
            StorageInitializer storageInitor = new StorageInitializer(countryService);
            File fileWithInitData = null;
            try {
                fileWithInitData = FileUtils
                        .createFileFromResource("init-data", ".xml", StorageInitorConstants.INIT_DATA_XML_FILE);
                storageInitor.initStorageWithCountriesAndCities(fileWithInitData.getAbsolutePath(), StorageInitializer.DataSourceType.XML_FILE);
            } catch (CheckedException e) {
                System.out.println("ERROR while init storage: " + e.getMessage());
                throw e;
            } catch (Exception e) {
                System.out.println("Error: Unknown magic :" + e.getMessage());
                throw e;
            } finally {
                if (fileWithInitData != null) {
                    Files.delete(Paths.get(fileWithInitData.toURI()));
                }
            }
            appendOrdersToUsers();

        }
        private void appendOrdersToUsers() {
            List<City> cities = cityService.findAll();
            List<User> users = userService.findAll();

            List<Order> orders = new ArrayList<>();
            int i = 0;
            for (User user : users) {
                i++;
                orders.add(prepareOrderForUser(user, cities));

                if (i % 2 == 0) {
                    orders.add(prepareOrderForUser(user, cities));
                }
            }

            for (Order order : orders) {
                orderService.add(order);
            }
        }
        private Order prepareOrderForUser(User user, List<City> cities) {
            Order order = new Order();
            order.setUser(user);
            City city = cities.get(getRandomInt(0, cities.size() - 1));
            order.setCity(city);


            return order;
        }
        public void printCities(){cityService.printAll();
        }
        public void demoReporting() {
            ReportProvider reportProvider = new ReportProvider(userService, orderService, cityService, countryService);

            File fileWithReport = null;
            try {
                fileWithReport = reportProvider.getUserOrdersTextFileReport();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                if (fileWithReport != null) {
                    System.out.println("File with report '" + fileWithReport.getAbsolutePath() + "'");
                    /*
                    //uncomment line to delete temp file
                    boolean deleted = fileWithReport.delete();
                    if (!deleted) {
                        System.out.println("OOps, can't delete file " + fileWithReport.getAbsolutePath());
                    }*/
                }
            }
        }
    }



    public static void main(String[] args) throws Exception {
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

        application.demoReporting();


    }


}
