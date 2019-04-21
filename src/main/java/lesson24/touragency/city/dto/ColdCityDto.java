package lesson24.touragency.city.dto;

import lesson24.touragency.city.domain.CityDiscriminator;

public class ColdCityDto extends  CityDto {
    private String coldestMonth;
    private int coldestTemp;
    @Override
    protected void initDiscriminator() {
        discriminator = CityDiscriminator.COLD;
    }
    @Override
    public String toString() {
        return "ColdCityDto{" +
                "coldestMonth='" + coldestMonth + '\'' +
                ", coldestTemp=" + coldestTemp +
                '}';
    }

    public String getColdestMonth() {
        return coldestMonth;
    }

    public void setColdestMonth(String coldestMonth) {
        this.coldestMonth = coldestMonth;
    }

    public int getColdestTemp() {
        return coldestTemp;
    }

    public void setColdestTemp(int coldestTemp) {
        this.coldestTemp = coldestTemp;
    }
}
