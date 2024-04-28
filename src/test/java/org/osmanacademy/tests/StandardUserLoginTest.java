package org.osmanacademy.tests;

import org.osmanacademy.dataobjects.LoginPageData;
import org.osmanacademy.exceptions.AutomationNextGenException;
import org.osmanacademy.pageactions.LoginPageAction;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class StandardUserLoginTest extends BaseTest {


    @Test
    public void test(ITestContext context) throws AutomationNextGenException {
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
            loginPageAction.functionalityLogin();
        } catch (AutomationNextGenException e) {
            getSoftAssert().assertAll();
            throw new RuntimeException(e);
        }

    }


}
