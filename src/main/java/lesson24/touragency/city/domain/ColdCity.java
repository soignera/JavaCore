package lesson24.touragency.city.domain;

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
