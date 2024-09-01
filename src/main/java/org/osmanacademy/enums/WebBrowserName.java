package org.osmanacademy.enums;

public enum WebBrowserName {
    CHROME("chrome"),
    FIREFOX("firefox"),
    EDGE("edge");

    private final String value;

    WebBrowserName(String value) {
        this.value = value;
    }

    public static WebBrowserName findByValue(String searchValue) {
        for (WebBrowserName browser : WebBrowserName.values()) {
            if (browser.value.equals(searchValue)) {
                return browser;
            }
        }
        throw new IllegalArgumentException();
    }

    public String getValue() {
        return value;
    }
}