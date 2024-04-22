package org.osmanacademy.applicationundertest.pageobjects;

import org.openqa.selenium.By;
import org.osmanacademy.webbrowser.WebBrowserAutomation;
import org.osmanacademy.webbrowser.exceptions.WebBrowserAutomationException;
public class LoginPageObject {
    // Web driver
    private WebBrowserAutomation webBrowserAutomation;

    // Constructor
    public LoginPageObject(WebBrowserAutomation webBrowserAutomation) {
        this.webBrowserAutomation = webBrowserAutomation;
    }

    private By pageTitle = By.xpath("//*[@class='login_logo']");
    private By usernameInput = By.id("user-name");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("login-button");

    public String getPageTitle() throws WebBrowserAutomationException {
        return webBrowserAutomation.getText(pageTitle);
    }
    public void enterUsername(String username) throws WebBrowserAutomationException {
        webBrowserAutomation.type(usernameInput,username);
    }

    public void enterPassword(String password) throws WebBrowserAutomationException {
        webBrowserAutomation.type(passwordInput,password);
    }

    public void clickLoginButton() throws WebBrowserAutomationException {
        webBrowserAutomation.click(loginButton);
    }

}
