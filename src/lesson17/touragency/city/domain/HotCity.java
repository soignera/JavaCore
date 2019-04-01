package lesson17.touragency.city.domain;

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
