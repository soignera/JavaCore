package lesson24.touragency.country.repo;

import lesson24.touragency.country.domain.Country;
import lesson24.touragency.country.search.CountryOrderByField;

import java.util.*;

import static lesson24.touragency.common.business.repo.CommonComparatorHolder.getComparatorForNullableStrings;
import static lesson24.touragency.country.search.CountryOrderByField.LANGUAG;
import static lesson24.touragency.country.search.CountryOrderByField.NAME;

public final class CountryComparatorComponent {
    private static final CountryComparatorComponent INSTANCE = new CountryComparatorComponent();
    private static Map<CountryOrderByField, Comparator<Country>> comparatorsByField = new HashMap<>();
    /**
     * For complex comparator only
     */
    private static Set<CountryOrderByField> fieldComparePriorityOrder = new LinkedHashSet<>(Arrays.asList(LANGUAG, NAME));

    static {
        comparatorsByField.put(LANGUAG, getComparatorForLanguagField());
        comparatorsByField.put(NAME, getComparatorForNameField());
    }

    private CountryComparatorComponent() {
    }


    public static CountryComparatorComponent getInstance() {
        return INSTANCE;
    }

    private static Comparator<Country> getComparatorForNameField() {
        return new Comparator<Country>() {
            @Override
            public int compare(Country country1, Country country2) {
                return getComparatorForNullableStrings().compare(country1.getName(), country2.getName());
            }
        };
    }

    private static Comparator<Country> getComparatorForLanguagField() {
        return new Comparator<Country>() {
            @Override
            public int compare(Country country1, Country country2) {
                return getComparatorForNullableStrings().compare(country1.getLanguag(), country1.getLanguag());
            }
        };
    }

    public Comparator<Country> getComparatorForField(CountryOrderByField field) {
        return comparatorsByField.get(field);
    }

    public Comparator<Country> getComplexComparator(CountryOrderByField field) {
        return new Comparator<Country>() {

            @Override
            public int compare(Country m1, Country m2) {
                int result = 0;
                Comparator<Country> countryComparator = comparatorsByField.get(field);

                if (countryComparator != null) {
                    result = countryComparator.compare(m1, m2);
                    //if records have same order priority, i want to order them in their group
                    if (result == 0) {

                        //loop throug all possible sorting fields
                        for (CountryOrderByField otherField : fieldComparePriorityOrder) {
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
