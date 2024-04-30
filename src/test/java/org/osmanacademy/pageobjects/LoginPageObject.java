package org.osmanacademy.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.osmanacademy.AutomationNextGen;
import org.osmanacademy.exceptions.AutomationNextGenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoginPageObject {

    private static final Logger logger = LoggerFactory.getLogger(LoginPageObject.class);
    private final ThreadLocal<AutomationNextGen> automationNextGen = new ThreadLocal<AutomationNextGen>();
    By textFieldUsername = By.id("user-name");
    By textFieldPassword = By.id("password");
    By buttonLogin = By.id("login-button");
    private String pageTitle;

    public LoginPageObject(AutomationNextGen automationNextGen) {
        this.automationNextGen.set(automationNextGen);
    }


    public void enterUsername(String username) throws AutomationNextGenException {
        try {
            this.automationNextGen.get().getWebBrowser().type(this.textFieldUsername, username);
        } catch (Exception e) {
            throw new AutomationNextGenException("Couldn't able to Enter Username: " ,e);
        }

    }

    @Step("Enter Password ::: {0}")
    public void enterPassword(String password) throws AutomationNextGenException {
        try {
            this.automationNextGen.get().getWebBrowser().type(this.textFieldPassword, password);
        } catch (Exception e) {
            throw new AutomationNextGenException("Couldn't able to Enter Password: " + e.getMessage());
        }
    }

    //Method to click on Login button
    @Step("Click Login Button ::: {0}")
    public void clickOnLoginButton() throws AutomationNextGenException {
        try {
            this.automationNextGen.get().getWebBrowser().click(this.buttonLogin);
        } catch (Exception e) {
            throw new AutomationNextGenException("Couldn't able to Click Login Button ::: " + e.getMessage());
        }
    }

}
