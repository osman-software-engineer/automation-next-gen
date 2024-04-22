package org.osmanacademy.enums;

public enum DateFormat {
    MM_DD_YYYY("MM/dd/yyyy"),
    YYYY_MM_DD("yyyy/dd/MM");
    private final String format;

    DateFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return this.format;
    }
}
