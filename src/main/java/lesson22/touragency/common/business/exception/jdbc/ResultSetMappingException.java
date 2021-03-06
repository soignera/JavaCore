package lesson22.touragency.common.business.exception.jdbc;

import lesson22.touragency.common.business.exception.UncheckedException;

import static lesson22.touragency.common.business.exception.CommonExceptionMeta.JDBC_RESULT_SET_MAPPING_ERROR;

public class ResultSetMappingException extends UncheckedException {
    public ResultSetMappingException(String entityClassName, Exception e) {
        super(JDBC_RESULT_SET_MAPPING_ERROR.getCode(), JDBC_RESULT_SET_MAPPING_ERROR.getDescriptionAsFormatStr(entityClassName), e);
    }
}
