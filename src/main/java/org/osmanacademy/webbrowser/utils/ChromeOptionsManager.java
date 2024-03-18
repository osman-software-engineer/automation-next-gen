package org.osmanacademy.webbrowser.utils;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.chrome.ChromeOptions;
import org.osmanacademy.webbrowser.config.AppConfig;

import org.osmanacademy.webbrowser.enums.EmulatedDevice;
import org.osmanacademy.webbrowser.enums.PropertyKeys;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class ChromeOptionsManager {

    private final ChromeOptions options;
    private final AppConfig webBrowserProperties;
    public static final String TRUE = "true";

    public ChromeOptionsManager() {
        this.options = new ChromeOptions();
        this.webBrowserProperties = new AppConfig("webbrowser.properties");
    }
    public void setOptions(ChromeOptionsArgs chromeOptionArgs) {
        this.options.addArguments(chromeOptionArgs.getArgs());
    }

    public ChromeOptions getChromeOptions() {
        return this.options;
    }

    public void manageStartMaximized(){
        String propKey = webBrowserProperties.getProperty(PropertyKeys.START_MAXIMIZED.getKeyName());
        if (StringUtils.equals(propKey,TRUE)) {
            setOptions(ChromeOptionsArgs.START_MAXIMIZED);
        }
    }

    public void manageMobileEmulation(){
        String propKey = webBrowserProperties.getProperty(PropertyKeys.MOBILE_EMULATION.getKeyName());
        if ((StringUtils.equals(propKey,TRUE))) {
            Map<String, String> mobileEmulation = new HashMap<>();
            try {
                String mobileDeviceName = webBrowserProperties.getProperty(PropertyKeys.MOBILE_DEVICE_NAME.getKeyName());
                boolean isFound = Arrays.stream(EmulatedDevice.values())
                        .anyMatch(device -> mobileDeviceName.equals(device.getDisplayName()));
                if (!isFound) {
                    throw new IllegalArgumentException();
                }
                mobileEmulation.put("deviceName", mobileDeviceName);
                getChromeOptions().setExperimentalOption("mobileEmulation", mobileEmulation);
            } catch (IllegalArgumentException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid device name. Possible causes Device name should be from one of the these names : ");
                for (EmulatedDevice device : EmulatedDevice.values()) {
                    sb.append(device.getDisplayName()).append(", ");
                }
                if (!sb.isEmpty()) {
                    sb.setLength(sb.length() - 2);
                }
                throw new IllegalArgumentException(sb.toString(), e);
            }
        }
    }
}