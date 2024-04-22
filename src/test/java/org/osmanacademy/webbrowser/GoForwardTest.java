package org.osmanacademy.webbrowser;

import org.osmanacademy.webbrowser.exceptions.WebBrowserAutomationException;
import org.osmanacademy.webbrowser.impl.SeleniumWebDriverImpl;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.fail;

public class GoForwardTest extends BaseTest {
    @BeforeTest
    public void beforeTest() {
        setApp(new SeleniumWebDriverImpl("web-browser-automation.properties"));
        setWebSiteUrl("https://www.google.com/");
        setSoftAssert(new SoftAssert());
    }
    @Test
    public void testGoForward() throws Exception {
        try {
            setApp(new SeleniumWebDriverImpl("web-browser-automation.properties"));
            getApp().openBrowser();
            getApp().maximize();
            getApp().openUrl(getWebSiteUrl());
            getApp().goForward();
            getApp().closeBrowser();
        } catch (Exception e) {
            fail("Exception was thrown during the testGoForward method execution: " + e.getMessage());
        }
    }
    @AfterTest
    public void afterTest() throws WebBrowserAutomationException {
        getApp().closeBrowser();
    }
}
