package lesson24.touragency.order.dto;

import lesson24.touragency.city.dto.CityDto;
import lesson24.touragency.common.business.dto.BaseDto;
import lesson24.touragency.country.domain.Country;
import lesson24.touragency.country.dto.CountryDto;

public class OrderDto extends BaseDto<Long> {
    private CountryDto countryDto;
    private CityDto cityDto;
    private UserDto user;
    private String description;
    private int price;

    public OrderDto() {
    }

    public CountryDto getCountryDto() {
        return countryDto;
    }

    public void setCountryDto(CountryDto countryDto) {
        this.countryDto = countryDto;
    }

    public CityDto getCityDto() {
        return cityDto;
    }

    public void setCityDto(CityDto cityDto) {
        this.cityDto = cityDto;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
