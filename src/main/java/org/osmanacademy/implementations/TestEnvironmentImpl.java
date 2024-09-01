package org.osmanacademy.implementations;

import org.osmanacademy.common.PropertiesFileLoader;
import org.osmanacademy.dataobjects.EnvironmentConfig;
import org.osmanacademy.enums.Environment;
import org.osmanacademy.interfaces.TestEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Properties;

public class TestEnvironmentImpl implements TestEnvironment {
    private static final Logger logger = LoggerFactory.getLogger(TestEnvironmentImpl.class);
    private final Properties envMasterProperties;
    private Properties envSpecificProperties;
    private EnvironmentConfig environmentConfig;


    public TestEnvironmentImpl() {
        this.envMasterProperties = new PropertiesFileLoader("environment/environment.properties").getProperties();
        loadEnvironments();
    }

    private void loadEnvironments() {
        try {
            logger.info("Trying to load environments");
            String env = getName();
            isValidEnvironment(env);
            String propertiesFileName = "environment" + File.separator + "environment-" + env + ".properties";
            this.envSpecificProperties = new PropertiesFileLoader(propertiesFileName).getProperties();
            loadEnvironmentConfiguration();
            logger.info("Successfully Loaded Environments");
        } catch (Exception e) {
            throw new RuntimeException("Exception Occurred while trying to load environments",e);
        }
    }

    @Override
    public String getName() {
        String env = System.getProperty("env");
        if (env == null) {
            env = envMasterProperties.getProperty("env");
        }
        return env;
    }

    public void isValidEnvironment(String environmentName) {
        try {
            Environment env;
            try {
                env = Environment.findByValue(environmentName);
            } catch (IllegalArgumentException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid Test Execution Environment Name.\nPossible Causes. " +
                        "\n1) Test Execution Environment Name Property ('env') in the 'environment.properties' file should be one of the following: ");
                for (Environment environment : Environment.values()) {
                    sb.append(environment.getValue()).append(", ");
                }
                throw new IllegalArgumentException(sb.toString() + ".", e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loadEnvironmentConfiguration() {
        try {
            logger.info("Trying to load environment configurations");
            this.environmentConfig = new EnvironmentConfig();
            this.environmentConfig.setWebsiteUrl(this.envSpecificProperties.getProperty("website.url"));
            // set database Properties
            loadDatabaseConfiguration(this.environmentConfig);
            // set web services properties
            loadWebServiceConfiguration(this.environmentConfig);
            logger.info("Successfully loaded environment configurations");
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred while Trying to load environment configuration",e);
        }
    }

    private void loadWebServiceConfiguration(EnvironmentConfig environmentConfig) {
        try {
            logger.info("Trying to load web services environments");
            environmentConfig.setWsServiceName(this.envSpecificProperties.getProperty("ws.service.name"));
            environmentConfig.setWsHostName(this.envSpecificProperties.getProperty("ws.host.name"));
            environmentConfig.setWsPortNumber(this.envSpecificProperties.getProperty("ws.port.number"));
            environmentConfig.setWsSchema(this.envSpecificProperties.getProperty("ws.schema"));
            environmentConfig.setWsUserId(this.envSpecificProperties.getProperty("ws.user.id"));
            environmentConfig.setWsUserPassword(this.envSpecificProperties.getProperty("ws.user.password"));
            logger.info("Successfully loaded web services environments");
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred while Trying to load web services environments",e);
        }
    }

    private void loadDatabaseConfiguration(EnvironmentConfig environmentConfig) {
        try {
            logger.info("Trying to database services environments");
            environmentConfig.setDbHostName(this.envSpecificProperties.getProperty("db.host.name"));
            environmentConfig.setDbName(this.envSpecificProperties.getProperty("db.name"));
            environmentConfig.setDbPortNumber(this.envSpecificProperties.getProperty("db.port.number"));
            environmentConfig.setDbUserId(this.envSpecificProperties.getProperty("db.user.id"));
            environmentConfig.setDbUserPassword(this.envSpecificProperties.getProperty("db.user.password"));
            logger.info("Successfully loaded database environments");
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred while Trying to load database environments",e);
        }
    }

    @Override
    public String getWebsiteURL() {
        return this.environmentConfig.getWebsiteUrl();
    }

    @Override
    public String isHigherEnvironment() {
        return null;
    }
}

