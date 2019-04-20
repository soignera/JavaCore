package lesson24.touragency.storage.initor.datasourcereader;

import lesson24.touragency.city.domain.City;
import lesson24.touragency.city.domain.CityDiscriminator;
import lesson24.touragency.city.domain.ColdCity;
import lesson24.touragency.city.domain.HotCity;
import lesson24.touragency.common.solution.parser.FileParser;
import lesson24.touragency.country.domain.Country;
import lesson24.touragency.storage.initor.checked.InvalidCityDiscriminatorException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static lesson24.touragency.storage.initor.exception.InitDataExceptionMeta.PARSE_CITY_DISCRIMINATOR_ERROR;

public class CountriesWithCitiesTxtFileParser implements FileParser<List<Country>> {

    private static final String COUNTRY_PLACEHOLDER = "Country:";

    @Override
    public List<Country> parseFile(String file) throws Exception {
        List<String> fileAsList = readFileToList(file);

        List<Country> result = new ArrayList<>();
        if (!fileAsList.isEmpty()) {
            List<List<String>> countriesWithCities = splitFileToSeparateCountriesWithCities(fileAsList);

            for (List<String> countryWithCities : countriesWithCities) {
                result.add(parseCountry(countryWithCities));
            }
        }

        return result;
    }

    private List<String> readFileToList(String file) throws IOException {
        List<String> fileAsList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileAsList.add(line);
            }
        }

        return fileAsList;
    }

    private List<List<String>> splitFileToSeparateCountriesWithCities(List<String> fileAsList) {
        List<List<String>> countriesWithCities = new ArrayList<>();

        List<String> singlCountryWithCities = null;
        for (String dataFromFile : fileAsList) {
            if (!dataFromFile.isEmpty()) {

                //check if country begin
                if (dataFromFile.contains(COUNTRY_PLACEHOLDER)) {
                    if (singlCountryWithCities != null && !singlCountryWithCities.isEmpty()) {
                        countriesWithCities.add(singlCountryWithCities);
                    }
                    singlCountryWithCities = new ArrayList<>();
                    singlCountryWithCities.add(dataFromFile);
                } else if (singlCountryWithCities != null) {
                    singlCountryWithCities.add(dataFromFile);
                }

            }
        }

        return countriesWithCities;
    }

    private Country parseCountry(List<String> countryWithCities) throws InvalidCityDiscriminatorException {
        String countryAsStr = countryWithCities.get(0).replaceAll(COUNTRY_PLACEHOLDER, "");
        countryWithCities.remove(0);

        String[] cityCsv = countryWithCities.toArray(new String[0]);
        return getCountry(countryAsStr, cityCsv);
    }

    private Country getCountry(String countryCsv, String[] citiesCsv) throws InvalidCityDiscriminatorException {
        String[] attrs = countryCsv.split("\\|");
        int attrIndex = -1;

        Country country = new Country(attrs[++attrIndex].trim(), attrs[++attrIndex].trim());
        country.setCities(new ArrayList<>());

        for (int i = 0; i < citiesCsv.length; i++) {
            String csvCity = citiesCsv[i];
            attrIndex = -1;
            attrs = csvCity.split("\\|");

            String discriminatorAsStr = attrs[++attrIndex].trim();
            City city = createCityByDiscriminator(discriminatorAsStr);
            city.setName(attrs[++attrIndex].trim());
            //city.setClimate((Climate)attrs[++attrIndex].trim());
            city.setPopulation(Integer.parseInt(attrs[++attrIndex].trim()));


            if (HotCity.class.equals(city.getClass())) {
                appendHotAttributes((HotCity) city, attrs, attrIndex);
            } else if (ColdCity.class.equals(city.getClass())) {
                appendColdAttributes((ColdCity) city, attrs, attrIndex);
            }

            country.getCities().add(city);
        }

        return country;
    }

    private City createCityByDiscriminator(String discriminatorAsStr) throws InvalidCityDiscriminatorException {
        return CityDiscriminator.getDiscriminatorByName(discriminatorAsStr)
                .map(cityDiscriminator -> {
                            if (CityDiscriminator.COLD.equals(cityDiscriminator)) {
                                return new ColdCity();
                            }
                            return new HotCity();
                        }
                )
                .orElseThrow(() -> new InvalidCityDiscriminatorException(
                        PARSE_CITY_DISCRIMINATOR_ERROR.getCode(),
                        PARSE_CITY_DISCRIMINATOR_ERROR.getDescriptionAsFormatStr(discriminatorAsStr)));
    }
//        if (CityDiscriminator.isDiscriminatorNotExists(discriminatorAsStr)) {
//            throw new InvalidCityDiscriminatorException(
//                    PARSE_CITY_DISCRIMINATOR_ERROR.getCode(),
//                    PARSE_CITY_DISCRIMINATOR_ERROR.getDescriptionAsFormatStr(discriminatorAsStr)
//            );
//        } else {
//            CityDiscriminator discriminator = CityDiscriminator.getDiscriminatorByName(discriminatorAsStr);
//            if (CityDiscriminator.COLD.equals(discriminator)) {
//                return new ColdCity();
//            }
//            return new HotCity();
//        }
//    }

    private void appendHotAttributes(HotCity city, String[] attrs, int attrIndex) {
        city.setHottestMonth(attrs[++attrIndex].trim());
        city.setHottestTemp(Integer.parseInt(attrs[++attrIndex].trim()));

    }

    private void appendColdAttributes(ColdCity city, String[] attrs, int attrIndex) {
        city.setColdestMonth(attrs[++attrIndex].trim());
        city.setColdestTemp(Integer.parseInt(attrs[++attrIndex].trim()));
    }
}
