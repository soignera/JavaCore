package touragency.country;

import touragency.city.City;

import java.util.Arrays;

public class Country {
    private String name;
    private String languag;
    private City[] cities;
    private Long id;

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", languag='" + languag + '\'' +
                ", cities=" + Arrays.toString(cities) +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguag() {
        return languag;
    }

    public void setLanguag(String languag) {
        this.languag = languag;
    }

    public City[] getCities() {
        return cities;
    }

    public void setCities(City[] cities) {
        this.cities = cities;
    }
}
