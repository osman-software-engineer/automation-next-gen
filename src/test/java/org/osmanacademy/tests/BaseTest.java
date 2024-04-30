package org.osmanacademy.tests;

import org.osmanacademy.AutomationNextGen;
import org.osmanacademy.implementations.AutomationNextGenImpl;
import org.osmanacademy.listeners.WebSuiteListener;
import org.osmanacademy.listeners.WebTestListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

/**
 * The BaseTestSmoke class represents the base test class for smoke tests. It provides methods for setting up and tearing down test environments, as well as accessing the web browser
 * and website URL.
 */
@Listeners({WebTestListener.class,WebSuiteListener.class})
public class BaseTest {

    public static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

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
    public void tearDown() throws Exception {
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
