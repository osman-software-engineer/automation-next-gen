package org.osmanacademy.implementations;

import org.osmanacademy.common.PropertiesFileLoader;
import org.osmanacademy.enums.Environment;
import org.osmanacademy.interfaces.TestExecutionEnvironment;

public class TestExecutionEnvironmentImpl implements TestExecutionEnvironment {

    private final ThreadLocal<PropertiesFileLoader> propertiesFileLoader = new ThreadLocal<PropertiesFileLoader>();

    public TestExecutionEnvironmentImpl() {
        propertiesFileLoader.set(new PropertiesFileLoader("test-environment.properties"));
    }

    /**
     * @return
     */
    @Override
    public String getTestExecutionEnvironment() {
        return propertiesFileLoader.get().getProperty("test.execution.environment");
    }

    /**
     * @return
     */
    @Override
    public String getWebsiteURL() throws IllegalArgumentException{
        Environment env;
        String testExecutionEnvironment = null;
        try {
            env = Environment.findByValue(getTestExecutionEnvironment());
        } catch (IllegalArgumentException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid Test Execution Environment Name.\nPossible Causes. " +
                    "\n1) Test Execution Environment Name in the test-environment.properties file should be one of the following: ");
            for (Environment environment : Environment.values()) {
                sb.append(environment.getValue()).append(", ");
            }
            if (!sb.isEmpty()) {
                sb.setLength(sb.length() - 2);
            }
            throw new IllegalArgumentException(sb.toString()+".", e);
        }
        testExecutionEnvironment = switch (env) {
            case DEV -> propertiesFileLoader.get().getProperty("dev.url.website");
            case INT -> propertiesFileLoader.get().getProperty("int.url.website");
            case SIT -> propertiesFileLoader.get().getProperty("sit.url.website");
            default -> testExecutionEnvironment;
        };
        return testExecutionEnvironment;
    }

    public static void main(String[] args) {
        TestExecutionEnvironmentImpl testEnvironment = new TestExecutionEnvironmentImpl();
        // Print the test execution environment
        System.out.println("Test Execution Environment: " + testEnvironment.getTestExecutionEnvironment());

        // Print the website URL
        try {
            System.out.println("Website URL: " + testEnvironment.getWebsiteURL());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

