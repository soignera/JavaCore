package lesson24.touragency.city.repo;//package lesson8.touragency.city.repo;

import lesson24.touragency.city.domain.City;
import lesson24.touragency.city.domain.CityDiscriminator;
import lesson24.touragency.city.domain.ColdCity;
import lesson24.touragency.city.domain.HotCity;
import lesson24.touragency.city.search.CitySearchCondition;
import lesson24.touragency.city.search.ColdCitySearchCondition;
import lesson24.touragency.city.search.HotCitySearchCondition;
import lesson24.touragency.common.business.search.Paginator;
import lesson24.touragency.common.solution.utils.ArrayUtils;
import lesson24.touragency.common.solution.utils.CollectionUtils;
import lesson24.touragency.common.solution.utils.OptionalUtils;
import lesson24.touragency.storage.AtomicSequenceGenerator;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lesson24.touragency.storage.Storage.citiesArray;

public class CityArrayRepo implements CityRepo {

    private int cityIndex = -1;

    @Override
    public City add(City city) {
        if (cityIndex == citiesArray.length - 1) {
            City[] newArrCities = new City[citiesArray.length * 2];
            System.arraycopy(citiesArray, 0, newArrCities, 0, citiesArray.length);
            citiesArray = newArrCities;
        }

        cityIndex++;
        city.setId(AtomicSequenceGenerator.getNextValue());
        citiesArray[cityIndex] = city;

        return city;
    }

    @Override
    public void add(Collection<City> cities) {
        cities.forEach(this::add);
    }

    @Override
    public Optional<City> findById(Long id) {
        return findCityIndexById(id).map(cityIndex -> citiesArray[cityIndex]);
    }

    @Override
    public void update(City city) {
        //we already in memory, no need to update object
    }

    @Override
    public List<? extends City> search(CitySearchCondition searchCondition) {
        CityDiscriminator cityDiscriminator = searchCondition.getCityDiscriminator();

        List<? extends City> result = Arrays.asList(citiesArray);

        switch (cityDiscriminator) {
            case COLD: {
                result = searchColdCities((ColdCitySearchCondition) searchCondition);
                break;
            }
            case HOT: {
                result = searchHotCities((HotCitySearchCondition) searchCondition);
                break;
            }
        }

        if (!result.isEmpty() && searchCondition.shouldPaginate()) {
            result = getPageableData(result, searchCondition.getPaginator());
        }

        return result;
    }

    @Override
    public List<City> getCitiesByCountryId(long countryId) {
        return Arrays.stream(citiesArray).filter(m -> m.getCountryId().equals(countryId)).collect(Collectors.toList());
    }

    private List<? extends City> getPageableData(List<? extends City> cities, Paginator paginator) {
        return CollectionUtils.getPageableData(cities, paginator.getLimit(), paginator.getOffset());
    }

    private List<HotCity> searchHotCities(HotCitySearchCondition searchCondition) {
        HotCity[] foundCities = new HotCity[citiesArray.length];
        int resultIndex = 0;

        for (City city : citiesArray) {

            if (CityDiscriminator.HOT.equals(city.getDiscriminator())) {
                HotCity hotCity = (HotCity) city;

                boolean found = true;
                if (searchCondition.searchByHottestTemp()) {
                    found = searchCondition.getHottestTemp().equals(hotCity.getHottestTemp());
                }

                if (found && searchCondition.searchByHottestMonth()) {
                    found = searchCondition.getHottestMonth().equals(hotCity.getHottestMonth());
                }

                if (found) {
                    foundCities[resultIndex] = hotCity;
                    resultIndex++;
                }
            }

        }

        if (resultIndex > 0) {
            HotCity toReturn[] = new HotCity[resultIndex];
            System.arraycopy(foundCities, 0, toReturn, 0, resultIndex);
            return new ArrayList<>(Arrays.asList(toReturn));
        }

        return Collections.emptyList();
    }


    private List<ColdCity> searchColdCities(ColdCitySearchCondition searchCondition) {

        ColdCity[] foundCities = new ColdCity[citiesArray.length];
        int resultIndex = 0;

        for (City city : citiesArray) {

            if (CityDiscriminator.COLD.equals(city.getDiscriminator())) {
                ColdCity coldCity = (ColdCity) city;

                boolean found = true;
                if (searchCondition.searchByColdestMonth()) {
                    found = searchCondition.getColdestMonth().equals(coldCity.getColdestMonth());
                }

                if (found && searchCondition.searchByColdestTemp()) {
                    found = searchCondition.getColdestTemp().equals(coldCity.getColdestTemp());
                }


                if (found) {
                    foundCities[resultIndex] = coldCity;
                    resultIndex++;
                }
            }

        }

        if (resultIndex > 0) {
            ColdCity[] toReturn = new ColdCity[resultIndex];
            System.arraycopy(foundCities, 0, toReturn, 0, resultIndex);
            return new ArrayList<>(Arrays.asList(toReturn));
        }

        return Collections.emptyList();
    }


    @Override
    public void deleteById(Long id) {
        findCityIndexById(id).ifPresent(this::deleteCityByIndex);
    }

    private void deleteCityByIndex(int index) {
        ArrayUtils.removeElement(citiesArray, index);
        cityIndex--;
    }

    @Override
    public void printAll() {
        Arrays.stream(citiesArray).filter(Objects::nonNull).forEach(System.out::println);
    }

    private Optional<Integer> findCityIndexById(long cityId) {
        OptionalInt optionalInt = IntStream.range(0, citiesArray.length).filter(i ->
                citiesArray[i] != null && Long.valueOf(cityId).equals(citiesArray[i].getId())
        ).findAny();

        return OptionalUtils.valueOf(optionalInt);
    }

    @Override
    public List<City> findAll() {
        return new ArrayList<>(Arrays.asList(citiesArray));
    }

    @Override
    public int countAll() {
        return citiesArray.length;
    }
}