package lesson22.touragency.city.search;

import lesson17.touragency.city.search.CityOrderByField;
import lesson17.touragency.common.business.search.BaseSearchCondition;

public class CitySearchCondition extends BaseSearchCondition<Long> {
    private String name;

    public lesson17.touragency.city.search.CityOrderByField getOrderByField() {
        return orderByField;
    }

    public void setOrderByField(lesson17.touragency.city.search.CityOrderByField orderByField) {
        this.orderByField = orderByField;
    }

    private CityOrderByField orderByField;

    private String climate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }
    public boolean needSearchByClimate(){
        return true;
    }

    public boolean isNameLengthGeThan6Chars(){
        return true;
    }
}

