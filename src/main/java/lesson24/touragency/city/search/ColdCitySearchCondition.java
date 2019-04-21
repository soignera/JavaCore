package lesson24.touragency.city.search;

public class ColdCitySearchCondition extends  CitySearchCondition {
    private String coldestMonth;
    private Integer coldestTemp;
    public boolean searchByColdestTemp() {
        return coldestTemp != null;
    }

    public boolean searchByColdestMonth() {
        return coldestMonth != null && !coldestMonth.isEmpty();
    }

    public String getColdestMonth() {
        return coldestMonth;
    }

    public void setColdestMonth(String coldestMonth) {
        this.coldestMonth = coldestMonth;
    }

    public Integer getColdestTemp() {
        return coldestTemp;
    }

    public void setColdestTemp(Integer coldestTemp) {
        this.coldestTemp = coldestTemp;
    }
}
