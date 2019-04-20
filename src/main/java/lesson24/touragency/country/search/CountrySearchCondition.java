package lesson24.touragency.country.search;

import lesson24.touragency.common.business.search.BaseSearchCondition;

import static lesson24.touragency.common.solution.utils.StringUtils.isNotBlank;

public class CountrySearchCondition extends BaseSearchCondition<Long> {
    private String name;
    private String languag;
    private CountryOrderByField orderByField;
    public boolean searchByName() {
        return isNotBlank(name);
    }

    public boolean searchByLanguag() {
        return isNotBlank(languag);
    }
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

    public CountryOrderByField getOrderByField() {
        return orderByField;
    }

    public void setOrderByField(CountryOrderByField orderByField) {
        this.orderByField = orderByField;} public boolean needOrdering() {
        return super.needOrdering() && orderByField != null;


}
}
