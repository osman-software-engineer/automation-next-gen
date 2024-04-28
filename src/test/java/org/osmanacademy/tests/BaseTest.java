package org.osmanacademy.tests;

import org.osmanacademy.AutomationNextGen;
import org.osmanacademy.exceptions.AutomationNextGenException;
import org.osmanacademy.implementations.AutomationNextGenImpl;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

/**
 * The BaseTestSmoke class represents the base test class for smoke tests. It provides methods for setting up and tearing down test environments, as well as accessing the web browser
 * and website URL.
 */
public class BaseTest {

    private AutomationNextGen automationNextGen;

    private SoftAssert softAssert;

    private String websiteUrl;

    @BeforeSuite
    public void beforeSuite() {
        // Place your code here
    }

    @BeforeTest
    public void setUp() {
        setAutomationNextGen( new AutomationNextGenImpl());
        setSoftAssert(new SoftAssert());
        setWebsiteUrl(getAutomationNextGen().getEnvironment().getWebsiteURL());
    }

    @AfterTest
    public void tearDown() throws AutomationNextGenException {
        getAutomationNextGen().getWebBrowser().closeBrowser();
    }
    @AfterSuite
    public void afterSuite() {
        // Place your code here
    }

    public AutomationNextGen getAutomationNextGen() {
        return automationNextGen;
    }

    public void setAutomationNextGen(AutomationNextGen automationNextGen) {
        this.automationNextGen = automationNextGen;
    }

    public SoftAssert getSoftAssert() {
        return softAssert;
    }

    public void setSoftAssert(SoftAssert softAssert) {
        this.softAssert = softAssert;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }
}
