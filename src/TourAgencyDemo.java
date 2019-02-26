import touragency.city.City;
import touragency.city.repo.CityMemoryRepo;
import touragency.city.service.CityDefaultService;
import touragency.city.service.CityService;
import touragency.storage.Storage;

import java.util.ArrayList;
import java.util.List;

public class TourAgencyDemo { private static class Application {
    private Storage storage = new Storage();
    private void addCities() {
        List listCitites= new ArrayList();
        listCitites.add(new City());

    }
    public void fillStorage() {
        addCities();

    }
    public void printCities() {
        cityService.printAll();
    }

    public static void main(String[] args) {
        Application application = new Application();
        application.fillStorage();
        System.out.println("--------Cities------------");
        application.printCities();
    }

    }}