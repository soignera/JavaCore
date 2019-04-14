package lesson24.touragency.common.business.exception;

public class UncheckedException extends RuntimeException {
    protected int code;

    public UncheckedException(int code, String message) {
        super(message);
        this.code = code;
    }

    public UncheckedException(int code, String message, Exception cause) {
        super(message);
        this.code = code;
        initCause(cause);
    }}
