package lesson24.touragency.storage.initor.checked;

import lesson24.touragency.common.business.exception.CheckedException;

public class InvalidCityDiscriminatorException extends CheckedException {
    public InvalidCityDiscriminatorException(int code, String message) {
        super(code, message);
    }
}
