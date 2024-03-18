package org.osmanacademy.webbrowser.utils;

public enum ChromeOptionsArgs {
    START_MAXIMIZED("--start-maximized"),
    HEADLESS("--headless"),
    INCOGNITO("--incognito"),
    DISABLE_EXTENSIONS("--disable-extensions"),
    WINDOW_SIZE("--window-size=1920,1080");

    private final String args;
    ChromeOptionsArgs(String args) {
        this.args = args;
    }
    public String getArgs() {
        return this.args;
    }
}
