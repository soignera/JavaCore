package lesson24.touragency.common.business.exception;

import static lesson24.touragency.common.business.exception.CommonExceptionMeta.JDBC_CONNECTION_ACHIVE_ERROR;

public class ConnectionAchiveError extends UncheckedException {
    public ConnectionAchiveError(Exception cause) {
        super(JDBC_CONNECTION_ACHIVE_ERROR.getCode(), JDBC_CONNECTION_ACHIVE_ERROR.getDescription(), cause);
    }
}
