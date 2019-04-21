package lesson24.touragency.city.domain;

public class ProxyCity extends City {
    private static final String ERROR_MESSAGE = "You deal with proxy! All operations not supporte, nut get/set Id";

    public ProxyCity(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public Climate getClimate() {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public void setClimate(Climate climate) {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public int getPopulation() {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public void setPopulation(int population) {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }


    @Override
    public CityDiscriminator getDiscriminator() {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public Long getCountryId() {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public void setCountryId(Long countryId) {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public String toString() {
        return "ID " + id;
    }

    @Override
    protected void initDiscriminator() {

    }
}
