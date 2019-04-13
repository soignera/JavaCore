package lesson22.touragency.city.repo;

import lesson17.touragency.city.domain.City;
import lesson17.touragency.city.search.CityOrderByField;

import java.util.*;

import static lesson17.touragency.city.search.CityOrderByField.CLIMATE;
import static lesson17.touragency.city.search.CityOrderByField.NAME;
import static lesson17.touragency.common.business.repo.CommonComparatorHolder.getComparatorForNullableStrings;

public final class CityComparatorComponent {
    private static final CityComparatorComponent INSTANCE = new CityComparatorComponent();
    private static Map<CityOrderByField, Comparator<City>> comparatorsByField = new HashMap<>();
    /**
     * For complex comparator only
     */
    private static Set<CityOrderByField> fieldComparePriorityOrder = new LinkedHashSet<>(Arrays.asList(CLIMATE, NAME));

    static {
        comparatorsByField.put(CLIMATE, getComparatorForCountryField());
        comparatorsByField.put(NAME, getComparatorForNameField());
    }

    private CityComparatorComponent() {
    }


    public static CityComparatorComponent getInstance() {
        return INSTANCE;
    }

    private static Comparator<City> getComparatorForNameField() {
        return new Comparator<City>() {
            @Override
            public int compare(City city1, City city2) {
                return getComparatorForNullableStrings().compare(city1.getName(), city2.getName());
            }
        };
    }

    private static Comparator<City> getComparatorForCountryField() {
        return new Comparator<City>() {
            @Override
            public int compare(City city1, City city2) {
                return getComparatorForNullableStrings().compare(city1.getClimate().toString(), city2.getClimate().toString());
            }
        };
    }

    public Comparator<City> getComparatorForField(CityOrderByField field) {
        return comparatorsByField.get(field);
    }

    public Comparator<City> getComplexComparator(CityOrderByField field) {
        return new Comparator<City>() {

            @Override
            public int compare(City m1, City m2) {
                int result = 0;
                Comparator<City> cityComparator = comparatorsByField.get(field);

                if (cityComparator != null) {
                    result = cityComparator.compare(m1, m2);
                    //if records have same order priority, i want to order them in their group
                    if (result == 0) {

                        //loop throug all possible sorting fields
                        for (CityOrderByField otherField : fieldComparePriorityOrder) {
                            //if i haven't sorted by field which is taken from parameter in function, i do sorting
                            if (!otherField.equals(field)) {

                                result = comparatorsByField.get(otherField).compare(m1, m2);
                                //if sort result detected that records are not equals - we exit from loop,
                                //else continue
                                if (result != 0) {
                                    break;
                                }
                            }
                        }

                    }
                }


                return result;
            }
        };
    }
}
