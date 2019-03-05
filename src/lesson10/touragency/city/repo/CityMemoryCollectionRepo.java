package lesson10.touragency.city.repo;

import lesson10.touragency.city.domain.City;
import lesson10.touragency.city.search.CitySearchCondition;
import lesson10.touragency.common.business.search.SortType;
import lesson10.touragency.storage.AtomicSequenceGenerator;


import java.util.*;

import static lesson10.touragency.storage.Storage.cities;
import static lesson10.touragency.storage.Storage.citiesList;

public class CityMemoryCollectionRepo implements CityRepo {
    private Set<City> cities;

    @Override
    public void add(City city) {
        city.setId(AtomicSequenceGenerator.getNextValue());
        citiesList.add(city);
    }


    @Override
    public City findById(long id) {
        return findCityById(id);
    }

    @Override
    public void update(City city) {
        //we already in memory, no need to update object
    }

    @Override
    public List<City> search(CitySearchCondition searchCondition) {

                if (searchCondition.getSortType().equals(SortType.ASC)) {
                    Collections.sort(citiesList);
                    return citiesList;
                }
                else  if (searchCondition.getSortType().equals(SortType.DESC)) {
                    //Comparator<City> comparator = Collections.reverseOrder();
                    Comparator<City> comparator = new Comparator<City>() {
                        @Override
                        public int compare(City o1, City o2) {
                            return o2.compareTo(o1);
                        }
                    };
                    Collections.sort(citiesList,comparator);
                    return citiesList;
                }
        return Collections.emptyList();

    }

    @Override
    public void deleteById(long id) {
        City found = findCityById(id);

        if (found != null) {
            citiesList.remove(found);
        }
    }

    @Override
    public void printAll() {
        for (City city : citiesList) {
            System.out.println(city);
        }
    }

    private City findCityById(long cityId) {
        for (City city : citiesList) {
            if (Long.valueOf(cityId).equals(city.getId())) {
                return city;
            }
        }
        return null;
    }
}
