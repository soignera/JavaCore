package lesson11.touragency.city.search;

public enum CityOrderByField {
    CLIMATE("cityclimate"),NAME("cityname");

    CityOrderByField(String requestParamName) {
        this.requestParamName = requestParamName;
    }

    private String requestParamName;

    public String getRequestParamName() {
        return requestParamName;
    }
}
