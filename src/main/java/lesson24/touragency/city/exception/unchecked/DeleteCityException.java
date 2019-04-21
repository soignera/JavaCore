package lesson24.touragency.city.exception.unchecked;

import lesson24.touragency.city.exception.CityExceptionMeta;
import lesson24.touragency.common.business.exception.UncheckedException;

public class DeleteCityException extends UncheckedException {
    public DeleteCityException(int code, String message) {
        super(code, message);
    }

    public DeleteCityException(CityExceptionMeta exceptionMeta) {
        super(exceptionMeta.getCode(), exceptionMeta.getDescription());
    }
}
