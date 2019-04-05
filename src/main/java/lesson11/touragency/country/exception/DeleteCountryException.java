package lesson11.touragency.country.exception;

import lesson11.touragency.common.business.exception.UncheckedException;
import lesson11.touragency.country.domain.Country;

public class DeleteCountryException extends UncheckedException {
    public DeleteCountryException(int code, String message) {
        super(code, message);
    }

    public DeleteCountryException(CountryExceptionMeta exceptionMeta) {
        super(exceptionMeta.getCode(), exceptionMeta.getDescription());
    }
}
