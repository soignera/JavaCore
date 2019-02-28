package lesson8.touragency.city.repo;

import lesson8.touragency.city.domain.City;
import lesson8.touragency.city.search.CitySearchCondition;
import lesson8.touragency.common.solution.ArrayUtils;

import static lesson8.touragency.common.solution.StringUtils.isNotBlank;
import static lesson8.touragency.storage.Storage.cities;

public class CityMemoryRepo implements CityRepo {
    private static final City[] EMPTY_CITIES_ARR = new City[0];
    private int cityIndex = -1;

    @Override
    public void add(City city) {
        if (cityIndex == cities.length - 1) {
            City[] newArrCitys = new City[cities.length * 2];
            System.arraycopy(cities, 0, newArrCitys, 0, cities.length);
            cities = newArrCitys;
        }

        cityIndex++;
        cities[cityIndex] = city;
    }

    @Override
    public City findById(long id) {
        Integer cityIndex = findCityIndexById(id);
        if (cityIndex != null) {
            return cities[cityIndex];
        }

        return null;
    }

    @Override
    public City[] search(CitySearchCondition searchCondition) {

        if (searchCondition.getId() != null) {
            return new City[]{findById(searchCondition.getId())};
        } else {
            boolean searchByClimate = isNotBlank(searchCondition.getClimate());

            boolean searchByName = isNotBlank(searchCondition.getName());

            City[] result = new City[cities.length];
            int resultIndex = 0;

            for (City city : cities) {
                if (city != null) {
                    boolean found = true;

                    if (searchByClimate) {
                        found = searchCondition.getClimate().equals(city.getClimate());
                    }

                    if (found && searchByName) {
                        found = searchCondition.getName().equals(city.getName());
                    }

                    if (found) {
                        result[resultIndex] = city;
                        resultIndex++;
                    }
                }
            }

            if (resultIndex > 0) {
                City toReturn[] = new City[resultIndex];
                System.arraycopy(result, 0, toReturn, 0, resultIndex);
                return toReturn;
            }
        }
        return EMPTY_CITIES_ARR;
    }

    @Override
    public void deleteById(long id) {
        Integer cityIndex = findCityIndexById(id);

        if (cityIndex != null) {
            deleteCityByIndex(cityIndex);
        }
    }

    private void deleteCityByIndex(int index) {
        ArrayUtils.removeElement(cities, index);
        cityIndex--;
    }

    @Override
    public void printAll() {
        for (City city : cities) {
            if (city != null) {
                System.out.println(city);
            }
        }
    }

    private Integer findCityIndexById(long cityId) {
        for (int i = 0; i < cities.length; i++) {
            if (cities[i] != null && Long.valueOf(cityId).equals(cities[i].getId())) {
                return i;
            }
        }
        return null;
    }
}
