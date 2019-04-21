package lesson24.touragency.city.dto;

import lesson24.touragency.city.domain.City;
import lesson24.touragency.city.domain.CityDiscriminator;
import lesson24.touragency.city.domain.ColdCity;
import lesson24.touragency.city.domain.HotCity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class CityDtoConverter {
    private CityDtoConverter() {

    }

    public static CityDto convertToDto(City city) {
        CityDto cityDto;
        if (CityDiscriminator.COLD.equals(city.getDiscriminator())) {
            ColdCity pm = (ColdCity) city;
            ColdCityDto dto = new ColdCityDto();
            dto.setColdestTemp(pm.getColdestTemp());
            dto.setColdestMonth(pm.getColdestMonth());

            cityDto = dto;
        } else {
            HotCity tm = (HotCity) city;
            HotCityDto dto = new HotCityDto();
            dto.setHottestTemp(tm.getHottestTemp());
            dto.setHottestMonth(tm.getHottestMonth());

            cityDto = dto;
        }

        cityDto.setId(city.getId());
        cityDto.setClimate(city.getClimate());
        cityDto.setName(city.getName());
        cityDto.setPopulation(city.getPopulation());

        return cityDto;
    }


    public static List<CityDto> convertToDtos(Collection<City> cities) {
        return cities.stream().map(CityDtoConverter::convertToDto).collect(Collectors.toList());
    }}
