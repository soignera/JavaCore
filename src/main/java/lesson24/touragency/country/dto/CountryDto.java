package lesson24.touragency.country.dto;

import lesson22.touragency.common.business.dto.BaseDto;

public class CountryDto extends BaseDto<Long> { private String name;
    private String languag;
    private List<CityDto> cities;

    public CountryDto() {
    }

    public String getName() {
        return name;
    }

    public List<CityDto> getCities() {
        return cities;
    }

    public void setCities(List<CityDto> cities) {
        this.cities = cities;
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

    public List<CityDto> getModels() {
        return cities;
    }

    public void setModels(List<CityDto> models) {
        this.cities = cities;
    }
}
