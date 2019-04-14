package lesson24.touragency.country.search;

public enum CountryOrderByField {
    LANGUAG("countrylanguag"),NAME("countryname");

    CountryOrderByField(String requestParamName) {
        this.requestParamName = requestParamName;
    }

    private String requestParamName;

    public String getRequestParamName() {
        return requestParamName;
    }
}
