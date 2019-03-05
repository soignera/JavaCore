package lesson10.touragency;

import lesson10.touragency.city.domain.City;
import lesson10.touragency.city.domain.Climate;
import lesson10.touragency.city.service.CityService;
import lesson10.touragency.common.business.application.ServiceSupplier;
import lesson10.touragency.common.business.application.StorageType;

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

        }
//private void addCities() {
//    String[] usersAsCsv = new String[]{
//            "Ivanov | 21",
//            "Petrov | 23",
//            "yuspov | 31",
//            "Jukova | 25",
//            "Belyh  | 23",
//            "T-800  | 125",
//            "T-1000  | 125",
//    };
//    Long id = 0L;
//    for (String csvUser : usersAsCsv) {
//        String[] userAttrs = csvUser.split("\\|");
//        int attrIndex = -1;
//        cityService.add(new City(
//                userAttrs[++attrIndex].trim(),
//                Integer.parseInt(userAttrs[++attrIndex].trim())
//        ));
//    }
//}



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
    }


}
