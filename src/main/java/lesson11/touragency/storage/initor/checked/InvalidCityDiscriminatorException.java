package lesson11.touragency.storage.initor.checked;

import lesson11.touragency.common.business.exception.CheckedException;

public class InvalidCityDiscriminatorException extends CheckedException {
    public InvalidCityDiscriminatorException(int code, String message) {
        super(code, message);
    }
}
