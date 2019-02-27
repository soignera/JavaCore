package touragency;

import touragency.city.City;
import touragency.city.Climate;
import touragency.city.repo.CityRepo;
import touragency.city.search.CitySearchCondition;
import touragency.city.service.CityDefaultService;
import touragency.city.service.CityService;
import touragency.storage.Storage;
import touragency.user.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TourAgencyDemo {
    private static class Application {
        private Storage storage = new Storage();
        private CityService cityService = CityDefaultService.getServiceInstance();




        private void addCities() {
            List<City> listCitites= new ArrayList<>();
            listCitites.add(new City("123",Climate.HUMID_CONTINENTAL,1,true));
            listCitites.add(new City("123",Climate.HUMID_CONTINENTAL,1,true));
            listCitites.add(new City("123",Climate.HUMID_CONTINENTAL,1,true));
        }
        public void fillStorage() {
            addCities();

        }
        public void printCities() {
           new Application().cityService.printAll();
        }

    public static void main(String[] args) {
        Application application = new Application();
        application.fillStorage();
        System.out.println("--------Cities------------");
        application.printCities();
    }}


}
