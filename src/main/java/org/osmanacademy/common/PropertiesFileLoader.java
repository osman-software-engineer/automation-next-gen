package org.osmanacademy.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public class PropertiesFileLoader {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesFileLoader.class);
    private final Properties properties;

    public PropertiesFileLoader(String propertiesFileName) {
        this.properties = loadPropertiesFromFile(propertiesFileName);
    }

    private Optional<InputStream> getPropertiesFileStream(String fileName) {
        InputStream input = getClass().getClassLoader().getResourceAsStream(fileName);
        return Optional.ofNullable(input);
    }

    private Properties loadPropertiesFromFile(String fileName) {
        logger.info("Trying to load properties from the file: {}", fileName);
        Properties properties = new Properties();
        try (InputStream input = getPropertiesFileStream(fileName).orElseThrow(() -> {
            String errorMessage = String.format("Properties file '%s' could not be found", fileName);
            logger.error(errorMessage);
            return new IllegalArgumentException(errorMessage);
        })) {
            properties.load(input);
            logger.info("Properties were loaded successfully from the file: {}", fileName);
        } catch (IOException e) {
            String errorWithLoading = String.format("An issue occurred while loading the properties from the file: '%s'", fileName);
            logger.error(errorWithLoading, e);
            throw new RuntimeException(errorWithLoading, e);
        }
        return properties;
    }

    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }

    public Properties getProperties() {
        return (Properties) properties.clone();
    }
}