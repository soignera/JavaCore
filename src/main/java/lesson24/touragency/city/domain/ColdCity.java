package lesson24.touragency.city.domain;

import lesson17.touragency.city.domain.City;
import lesson17.touragency.city.domain.CityDiscriminator;

public class ColdCity extends City {
    private String coldestMonth;
    private int coldestTemp;


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
    @Override
    protected void initDiscriminator() {
        discriminator = CityDiscriminator.COLD;
    }
}
