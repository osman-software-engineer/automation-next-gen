package org.osmanacademy.applicationundertest.smoke;

import org.osmanacademy.applicationundertest.pageactions.LoginPageAction;
import org.osmanacademy.webbrowser.BaseTest;
import org.osmanacademy.webbrowser.exceptions.WebBrowserAutomationException;
import org.osmanacademy.webbrowser.impl.SeleniumWebDriverImpl;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testStandardUserLogin extends BaseTest {
    @BeforeTest
    public void beforeTest() {
        setApp(new SeleniumWebDriverImpl("web-browser-automation.properties"));
        setWebSiteUrl("https://www.saucedemo.com/");
        setSoftAssert(new SoftAssert());
    }
    @Test
    public void testStandardUserLogin() throws Exception {
        try {
            getApp().openBrowser();
            getApp().maximize();
            getApp().openUrl(getWebSiteUrl());
            LoginPageAction loginPageAction = new LoginPageAction(getApp());
            loginPageAction.performLogin("standard_user","secret_sauce");
        } catch (WebBrowserAutomationException e) {
            throw new RuntimeException(e);
        }
    }
    @AfterTest
    public void afterTest() throws WebBrowserAutomationException {
        getApp().closeBrowser();
    }
}
