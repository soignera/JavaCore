package lesson24.touragency.country.domain;

import lesson24.touragency.city.domain.City;
import lesson24.touragency.common.business.domain.BaseDomain;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class Country extends BaseDomain<Long> {
    private String name;
    private String languag;
    private List<City> cities;



    public Country() {
    }
    public Country(Long id) {
        this.id = id;
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
    private String getCitiesAsStr() {
        StringBuilder stringBuilder = new StringBuilder();
        if (CollectionUtils.isNotEmpty(cities)) {
            for (City city : cities) {
                stringBuilder.append(city.toString()).append("\n");
            }
        }else{
            stringBuilder.append("No models");
        }

        return stringBuilder.toString();
    }

    public String getAsStrWithoutCitites() {
        return "id=" + id +
                ", languag='" + languag + '\'' +
                ", name='" + name + '\'';

    }

}
