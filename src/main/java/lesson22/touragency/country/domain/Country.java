package lesson22.touragency.country.domain;

import lesson17.touragency.city.domain.City;
import lesson17.touragency.common.business.domain.BaseDomain;

import java.util.List;

public class Country extends BaseDomain<Long> {
    private String name;
    private String languag;
    private List<City> cities;



    public Country() {
    }

    public Country(String name, String languag) {
        this.name = name;
        this.languag = languag;
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


    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public Long getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", languag='" + languag + '\'' +

                '}';
    }

}
