package lesson24.touragency.country.repo;

import lesson24.touragency.country.domain.Country;
import lesson24.touragency.country.repo.CountryComparatorComponent;
import lesson24.touragency.country.search.CountryOrderByField;
import lesson24.touragency.country.search.CountrySearchCondition;

import java.util.Comparator;
import java.util.List;

public class CountryOrderingComponent {
    public void applyOrdering(List<Country> countries, CountrySearchCondition countrySearchCondition) {
        Comparator<Country> countryComparator = null;

        CountryOrderByField field = countrySearchCondition.getOrderByField();
        switch (countrySearchCondition.getOrderType()) {

            case SIMPLE: {
                countryComparator = CountryComparatorComponent.getInstance().getComparatorForField(field);
                break;
            }
            case COMPLEX: {
                countryComparator = CountryComparatorComponent.getInstance().getComplexComparator(field);
                break;
            }
        }

        if (countryComparator != null) {
            switch (countrySearchCondition.getOrderDirection()) {

                case ASC:
                    countries.sort(countryComparator);
                    break;
                case DESC:
                    countries.sort(countryComparator.reversed());
                    break;
            }
        }
    }
}
