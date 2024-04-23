package org.osmanacademy.webbrowser;

import org.openqa.selenium.By;
import org.osmanacademy.webbrowser.enums.SelectAction;
import org.osmanacademy.webbrowser.exceptions.WebBrowserAutomationException;
import org.osmanacademy.webbrowser.impl.SeleniumWebDriverImpl;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SelectDropDownTest extends BaseTest {
    @BeforeTest
    public void beforeTest() {
        setWebBrowser(new SeleniumWebDriverImpl("web-browser-automation.properties"));
        setWebSiteUrl("https://the-internet.herokuapp.com/dropdown");
        setSoftAssert(new SoftAssert());
    }
    @Test
    public void testClick() throws Exception {
        try {
            getWebBrowser().openWebBrowser();
            getWebBrowser().maximize();
            getWebBrowser().openUrl(getWebSiteUrl());
            getWebBrowser().selectDropDown(By.xpath("//*[@id='dropdown']"), SelectAction.BY_VISIBLE_TEXT,"Option 1");

        } catch (WebBrowserAutomationException e) {
            throw new RuntimeException(e);
        }
    }
    @AfterTest
    public void afterTest() throws WebBrowserAutomationException {
        getWebBrowser().closeBrowser();
    }
}
