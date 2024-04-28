package org.osmanacademy.managers;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.chrome.ChromeOptions;

import org.osmanacademy.common.PropertiesFileLoader;
import org.osmanacademy.enums.ChromeOptionsArgs;
import org.osmanacademy.enums.EmulatedDevice;
import org.osmanacademy.enums.PropertyKeys;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class ChromeOptionsManager {

    private final ThreadLocal<ChromeOptions> options = new ThreadLocal<ChromeOptions>();
    private final ThreadLocal<PropertiesFileLoader> webBrowserProperties = new ThreadLocal<PropertiesFileLoader>();
    public static final String TRUE = "true";

    public ChromeOptionsManager(String propertyFileName) {
        this.options.set(new ChromeOptions());
        this.webBrowserProperties.set(new PropertiesFileLoader(propertyFileName));
    }
    public void setOptions(ChromeOptionsArgs chromeOptionArgs) {
        this.options.get().addArguments(chromeOptionArgs.getArgs());
    }

    public ChromeOptions getChromeOptions() {
        return this.options.get();
    }

    public void manageStartMaximized(){
        String propKey = webBrowserProperties.get().getProperty(PropertyKeys.START_MAXIMIZED.getKeyName());
        if (StringUtils.equals(propKey,TRUE)) {
            setOptions(ChromeOptionsArgs.START_MAXIMIZED);
        }
    }

    public void manageMobileEmulation(){
        String propKey = webBrowserProperties.get().getProperty(PropertyKeys.MOBILE_EMULATION.getKeyName());
        if ((StringUtils.equals(propKey,TRUE))) {
            Map<String, String> mobileEmulation = new HashMap<>();
            try {
                String mobileDeviceName = webBrowserProperties.get().getProperty(PropertyKeys.MOBILE_DEVICE_NAME.getKeyName());
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