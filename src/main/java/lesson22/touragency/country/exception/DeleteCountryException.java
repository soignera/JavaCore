package lesson22.touragency.country.exception;

import lesson22.touragency.common.business.exception.UncheckedException;

public class DeleteCountryException extends UncheckedException {
    public DeleteCountryException(int code, String message) {
        super(code, message);
    }

    public DeleteCountryException(CountryExceptionMeta exceptionMeta) {
        super(exceptionMeta.getCode(), exceptionMeta.getDescription());
    }
}
