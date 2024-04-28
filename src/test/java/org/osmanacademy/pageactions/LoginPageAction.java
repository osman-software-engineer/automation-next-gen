package org.osmanacademy.pageactions;

import org.osmanacademy.AutomationNextGen;
import org.osmanacademy.dataobjects.LoginPageData;
import org.osmanacademy.enums.Result;
import org.osmanacademy.exceptions.AutomationNextGenException;
import org.osmanacademy.pageobjects.LoginPageObject;

public class LoginPageAction {


    private LoginPageData loginPageData;

    private LoginPageObject loginPageObject;

    private AutomationNextGen automationNextGen;

    public LoginPageAction(LoginPageData loginPageData, AutomationNextGen automationNextGen) {
        setLoginPageData(loginPageData);
        setAutomationNextGen(automationNextGen);
        setLoginPageObject(new LoginPageObject(automationNextGen));
    }

   public Result functionalityLogin() throws AutomationNextGenException {
        try {
            getLoginPageObject().enterUsername(getLoginPageData().getUsername());
            getLoginPageObject().enterPassword(getLoginPageData().getPassword());
            getLoginPageObject().clickOnLoginButton();
            return Result.PASS;
        } catch (AutomationNextGenException e) {
            throw new AutomationNextGenException("Couldn't able to Login to Application ::: " + e.getMessage());
        }
    }

    public LoginPageData getLoginPageData() {
        return loginPageData;
    }

    public void setLoginPageData(LoginPageData loginPageData) {
        this.loginPageData = loginPageData;
    }

    public LoginPageObject getLoginPageObject() {
        return loginPageObject;
    }

    public void setLoginPageObject(LoginPageObject loginPageObject) {
        this.loginPageObject = loginPageObject;
    }

    public AutomationNextGen getAutomationNextGen() {
        return automationNextGen;
    }

    public void setAutomationNextGen(AutomationNextGen automationNextGen) {
        this.automationNextGen = automationNextGen;
    }
}
