package lesson10v2.touragency.city.search;

import lesson10v2.touragency.common.business.search.BaseSearchCondition;

public class CitySearchCondition extends BaseSearchCondition<Long> {
    private String name;

    public CityOrderByField getOrderByField() {
        return orderByField;
    }

    public void setOrderByField(CityOrderByField orderByField) {
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

