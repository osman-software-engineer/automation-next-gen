package org.osmanacademy.pageobjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.osmanacademy.AutomationNextGen;
import org.osmanacademy.tests.StandardUserLoginTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPageObject {
    private static final Logger logger = LoggerFactory.getLogger(LoginPageObject.class);
    private AutomationNextGen automationNextGen;

    public LoginPageObject(AutomationNextGen automationNextGen) {
        this.automationNextGen = automationNextGen;
    }

    /**
     * Enters the username into the username field.
     *
     * @param username The username to be entered.
     */
    public void enterUsername(String username) throws Exception {
        try {
            String locatorExpression = "#user-name";
            String webElementName = "username";
            this.automationNextGen.getWebBrowser().type(locatorExpression, webElementName, username);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void enterPassword(String password) throws Exception {
        try {
            String locatorExpression = "#password";
            String webElementName = "password";
            this.automationNextGen.getWebBrowser().type(locatorExpression, webElementName, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void clickLoginButton() throws Exception {
        try {
            String locatorExpression = "#login-button";
            String webElementName = "login button";
            this.automationNextGen.getWebBrowser().click(locatorExpression, webElementName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
