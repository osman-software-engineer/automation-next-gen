package org.osmanacademy.applicationundertest.pageactions;

import org.osmanacademy.applicationundertest.pageobjects.LoginPageObject;
import org.osmanacademy.webbrowser.WebBrowserAutomation;
import org.osmanacademy.webbrowser.exceptions.WebBrowserAutomationException;

public class LoginPageAction {
    private final ThreadLocal<LoginPageObject> loginPageObject = new ThreadLocal<LoginPageObject>();

    public LoginPageAction(WebBrowserAutomation webBrowserAutomation) {
        this.loginPageObject.set(new LoginPageObject(webBrowserAutomation));
    }

    public void performLogin(String username, String password) throws WebBrowserAutomationException {
        try {
            loginPageObject.get().enterUsername(username);
            loginPageObject.get().enterPassword(password);
            loginPageObject.get().clickLoginButton();
        } catch (WebBrowserAutomationException e) {
            throw new WebBrowserAutomationException(STR."Couldn't able to login into application \{e.getMessage()}");
        }
    }
}
