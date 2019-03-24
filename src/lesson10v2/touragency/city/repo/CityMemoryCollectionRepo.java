package lesson10v2.touragency.city.repo;

import lesson10v2.touragency.city.domain.City;
import lesson10v2.touragency.city.search.CitySearchCondition;
import lesson10v2.touragency.common.business.search.OrderType;
import lesson10v2.touragency.storage.AtomicSequenceGenerator;

import java.util.*;

import static lesson10v2.touragency.storage.Storage.citiesList;
import static lesson10v2.touragency.common.solution.StringUtils.isNotBlank;

public class CityMemoryCollectionRepo implements CityRepo {
    private Set<City> cities;
    private CityOrderingComponent orderingComponent = new CityOrderingComponent();


    @Override
    public void add(City city) {
        city.setId(AtomicSequenceGenerator.getNextValue());
        citiesList.add(city);
    }


    @Override
    public City findById(Long id) {
        return findCityById(id);
    }

    @Override
    public void update(City city) {
        //we already in memory, no need to update object
    }

    @Override
    public List<City> search(CitySearchCondition searchCondition) {
//
//                if (searchCondition.getOrderType().equals(OrderType.ASC)) {
//                    Collections.sort(citiesList);
//                    return citiesList;
//                }
//                else  if (searchCondition.getOrderType().equals(OrderType.DESC)) {
//                    //Comparator<City> comparator = Collections.reverseOrder();
//                    Comparator<City> comparator = new Comparator<City>() {
//                        @Override
//                        public int compare(City o1, City o2) {
//                            return o2.compareTo(o1);
//                        }
//                    };
//                    Collections.sort(citiesList,comparator);
//                    return citiesList;
//                }
//        return Collections.emptyList();
        if (searchCondition.getId() != null) {
            return Collections.singletonList(findById(searchCondition.getId()));
        } else {
            List<City> result = doSearch(searchCondition);

            boolean needOrdering = !result.isEmpty() && searchCondition.needOrdering();
            if (needOrdering) {
                orderingComponent.applyOrdering(result, searchCondition);
            }

            return result;
        }

    }
    private List<City> doSearch(CitySearchCondition searchCondition) {
        boolean searchByClimate = isNotBlank(searchCondition.getClimate());
        boolean searchByName = isNotBlank(searchCondition.getName());

        List<City> result = new ArrayList<>();
        for (City city : citiesList) {
            if (city != null) {
                boolean found = true;

                if (searchByClimate) {
                    found = searchCondition.getClimate().equals(city.getClimate());
                }

                if (found && searchByName) {
                    found = searchCondition.getName().equals(city.getName());
                }

                if (found) {
                    result.add(city);
                }
            }
        }
        return result;
    }


    @Override
    public void deleteById(Long id) {
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
