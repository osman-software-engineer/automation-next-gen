package org.osmanacademy.tests;

import org.osmanacademy.AutomationNextGen;
import org.osmanacademy.implementations.AutomationNextGenImpl;
import org.osmanacademy.pageobjects.LoginPageObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class StandardUserLoginTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(StandardUserLoginTest.class);
    private static final String TEST_NAME = "Standard User Login Test";
    private static final int maxRetryCount = 2;
    private int retryCount;
    private AutomationNextGen automationNextGen;
    private SoftAssert softAssert;

    @BeforeClass
    public void setUp() {
        setAutomationNextGen(new AutomationNextGenImpl());
        setSoftAssert( new SoftAssert());
    }

    @Test
    public void standardUserLoginTest(ITestContext context) throws Exception {
        try {
            //Launch Web Application
            getAutomationNextGen().getWebBrowser().openBrowser();

            getAutomationNextGen().getWebBrowser().navigateToURL(getAutomationNextGen().getTestEnvironment().getWebsiteURL());

            //Set Web Driver to TestNG context
            context.setAttribute("AutomationNextGen", getAutomationNextGen());

            LoginPageObject loginPageObject = new LoginPageObject(getAutomationNextGen());

            loginPageObject.enterUsername(getAutomationNextGen().getCredentials().getStandardUser().getUserName());
            loginPageObject.enterPassword(getAutomationNextGen().getCredentials().getStandardUser().getPassword());
            loginPageObject.clickLoginButton();

        } catch (Exception e) {
            getSoftAssert().assertNull("");
            context.setAttribute("Exception",e);
            getSoftAssert().assertAll();
        }
    }
    @AfterClass
    public void tearDown() {
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

}
