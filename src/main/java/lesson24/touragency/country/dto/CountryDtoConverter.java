package lesson24.touragency.country.dto;

import lesson24.touragency.city.dto.CityDtoConverter;
import lesson24.touragency.country.domain.Country;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public final class CountryDtoConverter {
    private CountryDtoConverter() {

    }

    public static CountryDto convertToDto(Country country) {
        CountryDto result = new CountryDto();
        result.setId(country.getId());
        result.setName(country.getName());
        result.setLanguag(country.getLanguag());

        if (CollectionUtils.isNotEmpty(country.getCities())) {
            result.setCities(country.getCities().stream().map(CityDtoConverter::convertToDto).collect(toList()));
        } else {
            result.setCities(Collections.emptyList());
        }
        return result;
    }

    public static List<CountryDto> convertToDtos(Collection<Country> countries) {
        return countries.stream().map(CountryDtoConverter::convertToDto).collect(toList());
    }}
