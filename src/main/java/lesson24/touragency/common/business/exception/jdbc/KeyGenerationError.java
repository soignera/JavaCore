package lesson24.touragency.common.business.exception.jdbc;

import lesson24.touragency.common.business.exception.UncheckedException;

import static lesson24.touragency.common.business.exception.CommonExceptionMeta.JDBC_KEY_GENERATION_ERROR;

public class KeyGenerationError extends UncheckedException {
    public KeyGenerationError(String generatedKey) {
        super(JDBC_KEY_GENERATION_ERROR.getCode(), JDBC_KEY_GENERATION_ERROR.getDescriptionAsFormatStr(generatedKey));
    }
}
