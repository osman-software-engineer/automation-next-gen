package org.osmanacademy.applicationundertest.pageactions;

import org.osmanacademy.applicationundertest.pageobjects.LoginPageObject;
import org.osmanacademy.webbrowser.WebBrowserAutomation;
import org.osmanacademy.webbrowser.exceptions.WebBrowserAutomationException;

public class LoginPageAction {
    private LoginPageObject loginPageObject;

    public LoginPageAction(WebBrowserAutomation webBrowserAutomation) {
        this.loginPageObject = new LoginPageObject(webBrowserAutomation);
    }

    public void performLogin(String username, String password) throws WebBrowserAutomationException {
        try {
            loginPageObject.enterUsername(username);
            loginPageObject.enterPassword(password);
            loginPageObject.clickLoginButton();
        } catch (WebBrowserAutomationException e) {
            throw new WebBrowserAutomationException("Couldn't able to login into application " + e.getMessage());
        }
    }
}
