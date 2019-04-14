package lesson24.touragency.common.business.exception;

public abstract class CheckedException extends Exception {
    protected int code;

    public CheckedException(int code, String message) {
        super(message);
        this.code = code;
    }

    public CheckedException(int code, String message, Exception cause) {
        super(message);
        this.code = code;
        initCause(cause);
    }
}
