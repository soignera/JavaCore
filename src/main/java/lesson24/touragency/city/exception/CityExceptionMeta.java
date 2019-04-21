package lesson24.touragency.city.exception;

public enum CityExceptionMeta  {
    DELETE_CITY_CONSTRAINT_ERROR(1, "Error while delete city. There is constraint violation!"),
    JDBC_UNKNOWN_CITY_DISCRIMINATOR_ERROR(20, "Unknown discriminator '%s'");

    private int code;
    private String description;

    CityExceptionMeta(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getDescriptionAsFormatStr(Object... args) {
        return String.format(description, args);
    }
}
