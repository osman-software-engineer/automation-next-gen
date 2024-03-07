package org.osmanacademy.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileLoader {

    private final Properties properties;

    public PropertiesFileLoader(String propertiesFileName) {
        this.properties = loadProperties(propertiesFileName);
    }

    private Properties loadProperties(String propertiesFileName) {
        Properties properties = new Properties();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(propertiesFileName)) {
            if (input == null) {
                throw new IllegalArgumentException("Properties file " + propertiesFileName + " not found");
            }
            properties.load(input);
        } catch (IOException io) {
            throw new RuntimeException("Could not load properties from " + propertiesFileName, io);
        }

        return properties;
    }

    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }
}