package lesson10.touragency.city.search;

import lesson10.touragency.common.business.search.BaseSearchCondition;

public class CitySearchCondition extends BaseSearchCondition {
    private String name;
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

