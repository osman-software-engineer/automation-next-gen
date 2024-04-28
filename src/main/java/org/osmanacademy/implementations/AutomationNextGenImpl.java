package org.osmanacademy.implementations;

import org.osmanacademy.AutomationNextGen;
import org.osmanacademy.enums.Environment;
import org.osmanacademy.interfaces.*;

public class AutomationNextGenImpl implements AutomationNextGen {


    private WebBrowser webBrowser;
    private TestDataGenerator testDataGenerator;
    private Database database;
    private TestExecutionEnvironment environment;
    private Credentials credentials;
    public AutomationNextGenImpl() {
        setWebBrowser(new SeleniumWebDriverImpl("web-browser-automation.properties"));
        setTestDataGenerator(new TestDataAutomationImpl());
        setDatabase(new DatabaseImpl());
        setCredentials(new CredentialsImpl());
        setEnvironment(new TestExecutionEnvironmentImpl());

    }

    /**
     * @return
     */
    @Override
    public WebBrowser getWebBrowser() {
        return this.webBrowser;
    }

    /**
     * @return
     */
    @Override
    public TestDataGenerator getTestDataGenerator() {
        return this.testDataGenerator;
    }

    /**
     * @return
     */
    @Override
    public Database getDatabase() {
        return this.database;
    }

    /**
     * @return
     */
    @Override
    public Credentials getCredentials() {
        return this.credentials;
    }

    /**
     * @return
     */
    @Override
    public TestExecutionEnvironment getEnvironment() {
        return this.environment;
    }

    public void setWebBrowser(WebBrowser webBrowser) {
        this.webBrowser = webBrowser;
    }

    public void setTestDataGenerator(TestDataGenerator testDataGenerator) {
        this.testDataGenerator = testDataGenerator;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public void setEnvironment(TestExecutionEnvironment environment) {
        this.environment = environment;
    }

}
