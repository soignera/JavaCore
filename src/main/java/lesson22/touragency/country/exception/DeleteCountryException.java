package lesson22.touragency.country.exception;

import lesson17.touragency.common.business.exception.UncheckedException;
import lesson17.touragency.country.exception.CountryExceptionMeta;

public class DeleteCountryException extends UncheckedException {
    public DeleteCountryException(int code, String message) {
        super(code, message);
    }

    public DeleteCountryException(CountryExceptionMeta exceptionMeta) {
        super(exceptionMeta.getCode(), exceptionMeta.getDescription());
    }
}
