package lesson24.touragency.city.dto;

import lesson24.touragency.city.domain.CityDiscriminator;

public class HotCityDto extends  CityDto {
    private String hottestMonth;
    private int hottestTemp;

    @Override
    public String toString() {
        return "HotCityDto{" +
                "hottestMonth='" + hottestMonth + '\'' +
                ", hottestTemp=" + hottestTemp +
                '}';
    }

    public String getHottestMonth() {
        return hottestMonth;
    }

    public void setHottestMonth(String hottestMonth) {
        this.hottestMonth = hottestMonth;
    }

    public int getHottestTemp() {
        return hottestTemp;
    }

    public void setHottestTemp(int hottestTemp) {
        this.hottestTemp = hottestTemp;
    }

    @Override
    protected void initDiscriminator() {
        discriminator = CityDiscriminator.HOT;
    }

}
