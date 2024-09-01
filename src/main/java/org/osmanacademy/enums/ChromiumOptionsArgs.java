package org.osmanacademy.enums;

public enum ChromiumOptionsArgs {
    START_MAXIMIZED("--start-maximized"),
    HEADLESS("--headless"),
    INCOGNITO("--incognito"),
    DISABLE_EXTENSIONS("--disable-extensions");
    private final String args;
    ChromiumOptionsArgs(String args) {
        this.args = args;
    }
    public String getArgs() {
        return this.args;
    }
}
