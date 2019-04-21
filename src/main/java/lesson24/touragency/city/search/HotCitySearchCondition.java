package lesson24.touragency.city.search;

public class HotCitySearchCondition extends CitySearchCondition {
    private String hottestMonth;
    private Integer hottestTemp;
    public boolean searchByHottestTemp() {
        return hottestTemp != null;
    }

    public boolean searchByHottestMonth() {
        return hottestMonth != null && !hottestMonth.isEmpty();
    }

    public String getHottestMonth() {
        return hottestMonth;
    }

    public void setHottestMonth(String hottestMonth) {
        this.hottestMonth = hottestMonth;
    }

    public Integer getHottestTemp() {
        return hottestTemp;
    }

    public void setHottestTemp(Integer hottestTemp) {
        this.hottestTemp = hottestTemp;
    }
}
