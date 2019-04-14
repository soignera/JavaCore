package lesson24.touragency;

import lesson22.touragency.city.domain.City;
import lesson22.touragency.city.search.CityOrderByField;
import lesson22.touragency.city.search.CitySearchCondition;
import lesson22.touragency.city.service.CityService;
import lesson22.touragency.common.business.application.ServiceSupplier;
import lesson22.touragency.common.business.application.StorageType;
import lesson22.touragency.common.business.exception.CheckedException;
import lesson22.touragency.common.business.search.OrderDirection;
import lesson22.touragency.common.business.search.OrderType;
import lesson22.touragency.common.business.search.Paginator;
import lesson22.touragency.country.domain.Country;
import lesson22.touragency.country.search.CountrySearchCondition;
import lesson22.touragency.country.service.CountryService;
import lesson22.touragency.order.domain.Order;
import lesson22.touragency.order.service.OrderService;
import lesson22.touragency.reporting.ReportProvider;
import lesson22.touragency.storage.initor.StorageInitializer;
import lesson22.touragency.user.domain.User;
import lesson22.touragency.user.service.UserService;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static lesson22.touragency.common.application.ApplicationConfigurations.*;
import static lesson22.touragency.common.solution.utils.FileUtils.createFileFromResource;
import static lesson22.touragency.common.solution.utils.RandomUtils.getRandomInt;

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

        public void fillStorage() {
            insertUsers();
            insertOrders();
        }

        private void insertUsers() {
            //List<City> listCitites= new ArrayList<>();
            userService.add(new User("123", "123",1));
            countryService.add(new Country("123", "123"));
            countryService.add(new Country("123", "123"));

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
        private Order prepareOrderForUser(User user, List<Country> countries) {
            Order order = new Order();
            order.setUser(user);
            Country country = countries.get(getRandomInt(0, countries.size() - 1));
            order.setCountry(country);
            order.setCity(country.getCities().get(getRandomInt(0, country.getCities().size() - 1)));
            order.setPrice(getRandomInt(1, 100000));
            List<String> problems = Arrays.asList("Не заводится",
                    "Еле едет", "Запах масла в салоне", "Стучит подвеска", "Ржавчина на пороге", "Помято крыло", "Не греют сиденья",
                    "Не включается дальний свет");

            order.setDescription(problems.get(getRandomInt(0, problems.size() - 1)));
            return order;
        }
        private void insertOrders() {
            List<Country> countries = countryService.findAllCountriesFetchingCities();
            List<User> users = userService.findAll();

            List<Order> orders = new ArrayList<>();
            int i = 0;
            for (User user : users) {
                i++;
                orders.add(prepareOrderForUser(user, countries));

                if (i % 2 == 0) {
                    orders.add(prepareOrderForUser(user, countries));
                }
            }
            orderService.add(orders);

            //test transaction
            orderService.update(orders.get(0));
        }
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
            //addCountries();
            StorageInitializer storageInitor = new StorageInitializer(countryService);
            List<File> filesWithInitData = null;
            try {
                filesWithInitData = getFilesWithDataToInit();
                storageInitor.initStorageWithCountriesAndCities(filesWithInitData, StorageInitializer.DataSourceType.XML_FILE);
            } catch (CheckedException e) {
                System.out.println("ERROR while init storage: " + e.getMessage());
                throw e;
            } catch (Exception e) {
                System.out.println("Error: Unknown magic :" + e.getMessage());
                throw e;
            } finally {
                if (filesWithInitData != null) {
                    for (File file : filesWithInitData) {
                        Files.delete(Paths.get(file.toURI()));
                    }
                }
            }
            appendOrdersToUsers();

        }

        private List<File> getFilesWithDataToInit() throws Exception {
            String files[] = new String[]{INIT_DATA_XML_FILE_PART_1, INIT_DATA_XML_FILE_PART_2};
            List<File> result = new ArrayList<>();

            for (String file : files) {
                result.add(createFileFromResource("init-data", ".txt", file));
            }
            return result;
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


                orderService.add(orders);

        }

        private Order prepareOrderForUser(User user, List<City> cities) {
            Order order = new Order();
            order.setUser(user);
            City city = cities.get(getRandomInt(0, cities.size() - 1));
            order.setCity(city);


            return order;
        }

        public void printCities() {
            cityService.printAll();
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

        public void searchCountriesWithPaginator() {
            System.out.println("\n-----------Search countries with paginator---------------------");
            int totalCountries = countryService.countAll();
            int totalPages = (int) Math.ceil((float) totalCountries / PAGE_SIZE);

            CountrySearchCondition searchCondition = new CountrySearchCondition();
            searchCondition.setPaginator(new Paginator());

            for (int i = 0; i < totalPages; i++) {
                searchCondition.getPaginator().setOffset(PAGE_SIZE * i);
                List<Country> found = countryService.search(searchCondition);

                if (!found.isEmpty()) {
                    int factLimit = found.size();
                    System.out.println("Display records on page from ["
                            + searchCondition.getPaginator().getOffset() + " - " +
                            (searchCondition.getPaginator().getOffset() + factLimit) + "]");

                    for (Country country : found) {
                        System.out.println(country);
                    }
                    System.out.println("----------");
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
//        application.searchCitiesWithOrderAsc();
//        application.searchCitiesWithOrderDesc();
//        application.searchCitiesWithComplexOrderAsc();
  //      application.searchCitiesWithComplexOrderDesc();

        //application.demoReporting();
        application.searchCountriesWithPaginator();
        application.searchCountriesWithoutOrder();


    }


}
