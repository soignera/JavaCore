package lesson24.touragency.city.repo;

import lesson24.touragency.city.domain.City;
import lesson24.touragency.city.domain.CityDiscriminator;
import lesson24.touragency.city.domain.ColdCity;
import lesson24.touragency.city.domain.HotCity;
import lesson24.touragency.city.search.CitySearchCondition;
import lesson24.touragency.city.search.ColdCitySearchCondition;
import lesson24.touragency.city.search.HotCitySearchCondition;
import lesson24.touragency.common.business.search.Paginator;
import lesson24.touragency.common.solution.utils.CollectionUtils;
import lesson24.touragency.storage.AtomicSequenceGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static lesson24.touragency.storage.Storage.citiesList;

public class CityCollectionRepo implements CityRepo {


    @Override
    public City add(City city) {
        city.setId(AtomicSequenceGenerator.getNextValue());
        citiesList.add(city);

        return city;
    }

    @Override
    public void add(Collection<City> cities) {
        for (City city : cities) {
            add(city);
        }
    }

    @Override
    public  Optional<City> findById(Long id) {
        return findCityById(id);
    }

    @Override
    public void update(City city) {
        //we already in memory, no need to update object
    }

    @Override
    public List<? extends City> search(CitySearchCondition searchCondition) {
        CityDiscriminator cityDiscriminator = searchCondition.getCityDiscriminator();

        List<? extends City> result = citiesList;

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
        return citiesList.stream().filter(m -> m.getCountryId().equals(countryId)).collect(Collectors.toList());
    }

    private List<? extends City> getPageableData(List<? extends City> cities, Paginator paginator) {
        return CollectionUtils.getPageableData(cities, paginator.getLimit(), paginator.getOffset());
    }

    private List<HotCity> searchHotCities(HotCitySearchCondition searchCondition) {
        List<HotCity> result = new ArrayList<>();

        for (City city : citiesList) {

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
                    result.add(hotCity);
                }
            }

        }

        return result;
    }

    private List<ColdCity> searchColdCities(ColdCitySearchCondition searchCondition) {
        List<ColdCity> result = new ArrayList<>();

        for (City city : citiesList) {

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
                    result.add(coldCity);
                }
            }

        }

        return result;
    }


    @Override
    public void deleteById(Long id) {
        findCityById(id).map(model -> citiesList.remove(model));}

    @Override
    public void printAll() {
        citiesList.forEach(System.out::println);}

    private Optional<City> findCityById(long cityId) {
        return citiesList.stream().filter(city -> Long.valueOf(cityId).equals(city.getId())).findAny();}
    @Override
    public List<City> findAll() {
        return citiesList;
    }
    @Override
    public int countAll() {
        return citiesList.size();
    }

}
