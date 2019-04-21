package lesson24.touragency.city.exception.unchecked;

import lesson24.touragency.common.business.exception.UncheckedException;

import static lesson24.touragency.city.exception.CityExceptionMeta.JDBC_UNKNOWN_CITY_DISCRIMINATOR_ERROR;

public class UnknownCityDiscriminatorException extends UncheckedException {
    public UnknownCityDiscriminatorException() {
    this(JDBC_UNKNOWN_CITY_DISCRIMINATOR_ERROR.getCode(), JDBC_UNKNOWN_CITY_DISCRIMINATOR_ERROR.getDescription());
}
    public UnknownCityDiscriminatorException(int code, String message) {
        super(code, message);
    }
}
