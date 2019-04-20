package lesson24.touragency.common.business.exception.jdbc;

import lesson24.touragency.common.business.exception.UncheckedException;

import static lesson24.touragency.common.business.exception.CommonExceptionMeta.JDBC_SQL_ERROR;

public class SqlError extends UncheckedException {

    public SqlError(Exception cause) {
        this(JDBC_SQL_ERROR.getCode(), JDBC_SQL_ERROR.getDescription(), cause);
    }

    private SqlError(int code, String message, Exception cause) {
        super(code, message, cause);
    }
}
