package org.osmanacademy.implementations;

import org.osmanacademy.AutomationNextGen;
import org.osmanacademy.interfaces.*;

public class AutomationNextGenImpl implements AutomationNextGen {


    private WebBrowser webBrowser;
    private TestDataGenerator testDataGenerator;
    private Database database;
    private TestEnvironment testEnvironment;
    private UserCredentials userCredentials;
    public AutomationNextGenImpl() {
        setTestEnvironment(new TestEnvironmentImpl());
        setWebBrowser(new PlaywrightImpl());
        setTestDataGenerator(new TestDataAutomationImpl());
        setDatabase(new DatabaseImpl());
        setCredentials(new UserCredentialsImpl());

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
    public UserCredentials getCredentials() {
        return this.userCredentials;
    }

    /**
     * @return
     */
    @Override
    public TestEnvironment getTestEnvironment() {
        return this.testEnvironment;
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

    public void setCredentials(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }

    public void setTestEnvironment(TestEnvironment testEnvironment) {
        this.testEnvironment = testEnvironment;
    }

}
