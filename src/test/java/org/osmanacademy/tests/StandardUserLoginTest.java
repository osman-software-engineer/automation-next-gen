package org.osmanacademy.tests;

import org.osmanacademy.dataobjects.LoginPageData;
import org.osmanacademy.exceptions.AutomationNextGenException;
import org.osmanacademy.pageactions.LoginPageAction;
import org.osmanacademy.pageobjects.LoginPageObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class StandardUserLoginTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(LoginPageObject.class);

    @Test
    public void standardUserLoginTest(ITestContext context) throws AutomationNextGenException {
        try {
            //Launch Web Application
            getAutomationNextGen().getWebBrowser().openBrowser();
            getAutomationNextGen().getWebBrowser().openUrl(getWebsiteUrl());
            getAutomationNextGen().getWebBrowser().maximize();
            //Set Web Driver to TestNG context
            context.setAttribute("AutomationNextGen", getAutomationNextGen());

            //Set Login Page Data
            LoginPageData loginPageData = new LoginPageData();
            loginPageData.setUsername(getAutomationNextGen().getCredentials().getStandardUser().getUserName());
            loginPageData.setPassword(getAutomationNextGen().getCredentials().getStandardUser().getPassword());

            // Perform Login Functionality
            LoginPageAction loginPageAction = new LoginPageAction(loginPageData,getAutomationNextGen());
            loginPageAction.performLogin();

        } catch (Exception e) {
            getSoftAssert().assertNull(e, STR."Expected no exception, but found: \{e}");
            context.setAttribute("Exception",e);
            getSoftAssert().assertAll();
        }

    }


}
