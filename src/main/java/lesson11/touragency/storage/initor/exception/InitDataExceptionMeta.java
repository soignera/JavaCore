package lesson11.touragency.storage.initor.exception;

public enum InitDataExceptionMeta {
    PARSE_CITY_DISCRIMINATOR_ERROR(1, "Unknown city discriminator '%s'.");

    private int code;
    private String description;

    InitDataExceptionMeta(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getDescriptionAsFormatStr(Object... args) {
        return String.format(description, args);
    }
}
