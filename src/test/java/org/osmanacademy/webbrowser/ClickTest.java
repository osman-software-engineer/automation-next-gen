package org.osmanacademy.webbrowser;

import org.openqa.selenium.By;
import org.osmanacademy.webbrowser.exceptions.WebBrowserAutomationException;
import org.osmanacademy.webbrowser.impl.SeleniumWebDriverImpl;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ClickTest extends BaseTest {
    @BeforeTest
    public void beforeTest() {
        setApp(new SeleniumWebDriverImpl("web-browser-automation.properties"));
        setWebSiteUrl("https://www.google.com/");
        setSoftAssert(new SoftAssert());
    }
    @Test
    public void testClick() throws Exception {
        try {
            getApp().openBrowser();
            getApp().maximize();
            getApp().openUrl(getWebSiteUrl());
            getApp().click(By.xpath("//a[contains(text(), 'About')]"));

        } catch (WebBrowserAutomationException e) {
            throw new RuntimeException(e);
        }
    }
    @AfterTest
    public void afterTest() throws WebBrowserAutomationException {
        getApp().closeBrowser();
    }
}
