package lesson24.touragency.city.dto;

import lesson24.touragency.city.domain.CityDiscriminator;
import lesson24.touragency.city.domain.Climate;
import lesson24.touragency.common.business.dto.BaseDto;

public abstract class CityDto extends BaseDto<Long> {
    protected String name;
    protected Climate climate;
    protected int population;
    protected CityDiscriminator discriminator;

    public CityDto() {
        initDiscriminator();
    }

    protected abstract void initDiscriminator();

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


    public CityDiscriminator getDiscriminator() {
        return discriminator;
    }

}
