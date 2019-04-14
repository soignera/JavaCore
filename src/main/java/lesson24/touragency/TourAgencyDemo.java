package lesson24.touragency;//package lesson11.touragency;
//
//import lesson11.touragency.city.domain.City;
//import lesson11.touragency.city.service.CityDefaultService;
//import lesson11.touragency.city.service.CityService;
//
//public class TourAgencyDemo {
//    private static class Application {
//        private CityService cityService = CityDefaultService.getServiceInstance();
//
//
//
//
////        private void addCities() {
////            List<City> listCitites= new ArrayList<>();
////            listCitites.add(new City("123",Climate.HUMID_CONTINENTAL,1,true));
////            listCitites.add(new City("123",Climate.HUMID_CONTINENTAL,1,true));
////            listCitites.add(new City("123",Climate.HUMID_CONTINENTAL,1,true));
////
////        }
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
//
//
//
//        public void fillStorage() {
//            addCities();
//
//        }
//        public void printCities() {
//           new Application().cityService.printAll();
//        }
//
//    }
//    public static void main(String[] args) {
//        Application application = new Application();
//        application.fillStorage();
//        System.out.println("--------Cities------------");
//        application.printCities();
//    }
//
//
//}
