package lesson24.touragency.city.domain;

import lesson17.touragency.city.domain.City;
import lesson17.touragency.city.domain.CityDiscriminator;

public class HotCity extends City {
    private String hottestMonth;
    private int hottestTemp;


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
        discriminator = CityDiscriminator.COLD;
    }
}
