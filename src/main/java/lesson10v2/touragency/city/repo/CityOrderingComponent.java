package lesson10v2.touragency.city.repo;

import lesson10v2.touragency.city.domain.City;
import lesson10v2.touragency.city.search.CityOrderByField;
import lesson10v2.touragency.city.search.CitySearchCondition;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static lesson10v2.touragency.common.business.search.OrderDirection.ASC;
import static lesson10v2.touragency.common.business.search.OrderDirection.DESC;
import static lesson10v2.touragency.common.business.search.OrderType.COMPLEX;
import static lesson10v2.touragency.common.business.search.OrderType.SIMPLE;

public class CityOrderingComponent {
    class MyCompararable implements Comparable<String>{

        private String srcString;
        private boolean invert = false;

        public MyCompararable(String srcString) {
            this.srcString = srcString;
        }

        @Override
        public int compareTo(String o) {
            if (invert) {
                return (-1) * this.srcString.compareTo(o);
            }else{
                return  this.srcString.compareTo(o);
            }
        }
    }

    public void applyOrdering(List<City> cities, CitySearchCondition citySearchCondition) {
        Comparator<City> cityComparator = null;

        CityOrderByField field = citySearchCondition.getOrderByField();
        switch (citySearchCondition.getOrderType()) {

            case SIMPLE: {
                cityComparator = CityComparatorComponent.getInstance().getComparatorForField(field);
                break;
            }
            case COMPLEX: {
                cityComparator = CityComparatorComponent.getInstance().getComplexComparator(field);
                break;
            }
        }

        if (cityComparator != null) {
            switch (citySearchCondition.getOrderDirection()) {

                case ASC:
                    Collections.sort(cities, cityComparator);
                    break;
                case DESC:
                    Collections.sort(cities, cityComparator.reversed());
                    break;
            }
        }
    }

}
