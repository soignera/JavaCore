package lesson24.touragency.user.exception.unchecked;

import lesson24.touragency.common.business.exception.UncheckedException;

import static lesson24.touragency.user.exception.UserExceptionMeta.JDBC_UNKNOWN_USER_CLIENT_TYPE_ERROR;

public class UnknownUserClientTypeException extends UncheckedException {   public UnknownUserClientTypeException(String unknownClientType) {
    super(JDBC_UNKNOWN_USER_CLIENT_TYPE_ERROR.getCode(), JDBC_UNKNOWN_USER_CLIENT_TYPE_ERROR.getDescriptionAsFormatStr(unknownClientType));
}
}
