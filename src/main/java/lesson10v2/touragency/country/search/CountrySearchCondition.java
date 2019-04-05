package lesson10v2.touragency.country.search;

import lesson10v2.touragency.common.business.search.BaseSearchCondition;

public class CountrySearchCondition extends BaseSearchCondition {
    private String name;
    private String languag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguag() {
        return languag;
    }

    public void setLanguag(String languag) {
        this.languag = languag;
    }
    public boolean needSearchByLanguag(){
        return true;
    }

    public boolean isNameLengthGeThan6Chars(){
        return true;
    }
}
