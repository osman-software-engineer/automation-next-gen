package org.osmanacademy.webbrowser.config;

import org.osmanacademy.common.PropertiesFileLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig extends Properties{

    private final Properties properties;

    public AppConfig(String propertiesFileName) {
        this.properties = new PropertiesFileLoader(propertiesFileName).getProperties();
    }

    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }
}