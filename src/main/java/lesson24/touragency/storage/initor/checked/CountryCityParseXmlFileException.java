package lesson24.touragency.storage.initor.checked;

import lesson24.touragency.common.business.exception.CheckedException;


public class CountryCityParseXmlFileException extends CheckedException {
    public CountryCityParseXmlFileException(int code, String message, Exception cause){
        super(code, message);
        initCause(cause);
    }
}
