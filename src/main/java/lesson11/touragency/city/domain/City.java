package lesson11.touragency.city.domain;

import lesson11.touragency.common.business.domain.BaseDomain;

public abstract class City extends BaseDomain implements Comparable<City>
{

        //private Long id;
        private String name;
        private Climate climate;
        private int population;
        private boolean capital;
        protected CityDiscriminator discriminator;

    public City() {
       initDiscriminator();
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
    protected abstract void initDiscriminator();
    public CityDiscriminator getDiscriminator() {
        return discriminator;
    }

    @Override
    public int compareTo(City o) {
        return Integer.compare(this.id.intValue(),o.id.intValue());
    }
}
