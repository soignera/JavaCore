package lesson10v2.touragency.city.search;

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
