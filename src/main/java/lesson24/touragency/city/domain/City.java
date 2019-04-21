package lesson24.touragency.city.domain;

import lesson24.touragency.common.business.domain.BaseDomain;

public abstract class City extends BaseDomain<Long>
{

        //private Long id;
        private String name;
        private Climate climate;
        private int population;
        private boolean capital;
        protected CityDiscriminator discriminator;
    protected Long countryId;

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

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
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

}
