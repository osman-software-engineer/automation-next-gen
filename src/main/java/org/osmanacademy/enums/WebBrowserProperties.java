package org.osmanacademy.enums;

public enum WebBrowserProperties {
    BROWSER_EXECUTION_LOCATION("web.browser.execution.location"),
    BROWSER_NAME("web.browser.name"),
    HEADLESS("headless"),
    START_MAXIMIZED("start.maximized"),
    INCOGNITO("incognito"),
    DISABLE_EXTENSIONS("disable.extensions"),
    MOBILE_EMULATION("mobile.emulation"),
    WINDOW_SIZE("window.size"),
    WINDOW_WIDTH("web.browser.window.size.width"),
    WINDOW_HEIGHT("web.browser.window.size.height"),
    MOBILE_DEVICE_NAME("mobile.device.name");

    private final String keyName;

    WebBrowserProperties(String keyName) {
        this.keyName = keyName;
    }

    public String getKeyName() {
        return keyName;
    }
}