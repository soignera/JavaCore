package lesson10v2.touragency.city.domain;

import lesson10v2.touragency.common.business.domain.BaseDomain;

public class City extends BaseDomain implements Comparable<City>
{

        //private Long id;
        private String name;
        private Climate climate;
        private int population;
        private boolean capital;

    public City(String name, int population) {
        this.name = name;
        this.population = population;
    }

    public City(String name, Climate climate, int population, boolean capital) {
        this.name = name;
        this.climate = climate;
        this.population = population;
        this.capital = capital;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Climate getClimate() {
        return climate;
    }

    public void setClimate(Climate climate) {
        this.climate = climate;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public boolean isCapital() {
        return capital;
    }

    public void setCapital(boolean capital) {
        this.capital = capital;
    }

    @Override
    public String toString() {
        return "City{" +
               "id=" + id +
                ", name='" + name + '\'' +
                ", climate=" + climate +
                ", population=" + population +
                ", capital=" + capital +
                '}';
    }

    @Override
    public int compareTo(City o) {
        return Integer.compare(this.id.intValue(),o.id.intValue());
    }
}
