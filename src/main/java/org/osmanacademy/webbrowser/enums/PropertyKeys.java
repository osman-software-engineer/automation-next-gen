package org.osmanacademy.webbrowser.enums;

public enum PropertyKeys {
    BROWSER_EXECUTION_LOCATION("web.browser.execution.location"),
    BROWSER_NAME("web.browser.name"),
    HEADLESS("headless"),
    START_MAXIMIZED("start.maximized"),
    INCOGNITO("incognito"),
    DISABLE_EXTENSIONS("disable.extensions"),
    MOBILE_EMULATION("mobile.emulation"),
    MOBILE_DEVICE_NAME("mobile.device.name");

    private final String keyName;

    PropertyKeys(String keyName) {
        this.keyName = keyName;
    }

    public String getKeyName() {
        return keyName;
    }
}