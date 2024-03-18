package org.osmanacademy.webbrowser;


import org.openqa.selenium.By;
import org.osmanacademy.webbrowser.impl.SeleniumWebDriverImpl;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;

public class SeleniumWebDriverTest {

    public static final String WEBSITE_URL = "https://www.google.com/";

    @Test
    public void testOpenBrowser() {
        try {
            WebBrowserAutomation app = new SeleniumWebDriverImpl();
            app.openBrowser();
            app.closeBrowser();
        } catch (Exception e) {
            fail("Exception was thrown during the openBrowser method execution: " + e.getMessage());
        }
    }

    @Test
    public void testMaximize() {
        try {
            WebBrowserAutomation app = new SeleniumWebDriverImpl();
            app.openBrowser();
            app.maximize();
            app.closeBrowser();
        } catch (Exception e) {
            fail("Exception was thrown during the testMaximize method execution: " + e.getMessage());
        }
    }

    @Test
    public void testMinimize() {
        try {
            WebBrowserAutomation app = new SeleniumWebDriverImpl();
            app.openBrowser();
            app.minimize();
            app.closeBrowser();
        } catch (Exception e) {
            fail("Exception was thrown during the testMaximize method execution: " + e.getMessage());
        }
    }
    @Test
    public void testOpenUrl() {
        try {
            WebBrowserAutomation app = new SeleniumWebDriverImpl();
            app.openBrowser();
            app.maximize();
            app.openUrl(WEBSITE_URL);
            app.closeBrowser();
        } catch (Exception e) {
            fail("Exception was thrown during the testOpenUrl method execution: " + e.getMessage());
        }
    }

    @Test
    public void testRefresh() {
        try {
            WebBrowserAutomation app = new SeleniumWebDriverImpl();
            app.openBrowser();
            app.maximize();
            app.openUrl(WEBSITE_URL);
            app.maximize();
            app.refresh();
            app.closeBrowser();
        } catch (Exception e) {
            fail("Exception was thrown during the testRefresh method execution: " + e.getMessage());
        }
    }

    @Test
    public void testOpenNewTab() {
        try {
            WebBrowserAutomation app = new SeleniumWebDriverImpl();
            app.openBrowser();
            app.maximize();
            app.openUrl(WEBSITE_URL);
            app.openNewTab();
            app.switchToTabWithTitle("New Tab");
            app.closeBrowser();
        } catch (Exception e) {
            fail("Exception was thrown during the testOpenNewTab method execution: " + e.getMessage());
        }
    }

    @Test
    public void testSwitchToTabWithTitle() {
        try {
            WebBrowserAutomation app = new SeleniumWebDriverImpl();
            app.openBrowser();
            app.maximize();
            app.openUrl(WEBSITE_URL);
            app.openNewTab();
            app.switchToTabWithTitle("New Tab");
            app.closeBrowser();
        } catch (Exception e) {
            fail("Exception was thrown during the testSwitchToTabWithTitle method execution: " + e.getMessage());
        }
    }

    @Test
    public void testGoBack() throws Exception {
        try {
        WebBrowserAutomation app = new SeleniumWebDriverImpl();
        app.openBrowser();
        app.maximize();
        app.openUrl(WEBSITE_URL);
        app.goBack();
        app.closeBrowser();
        } catch (Exception e) {
            fail("Exception was thrown during the testGoBack method execution: " + e.getMessage());
        }
    }

    @Test
    public void testGoForward() throws Exception {
        try {
            WebBrowserAutomation app = new SeleniumWebDriverImpl();
            app.openBrowser();
            app.maximize();
            app.openUrl(WEBSITE_URL);
            app.goForward();
            app.closeBrowser();
        } catch (Exception e) {
            fail("Exception was thrown during the testGoForward method execution: " + e.getMessage());
        }
    }


    @Test
    public void testClick() throws Exception {
        WebBrowserAutomation app = new SeleniumWebDriverImpl();
        app.openBrowser();
        app.maximize();
        app.openUrl(WEBSITE_URL);
        app.click(By.xpath("//a[contains(text(), 'About')]"));
        app.closeBrowser();
    }

    @Test
    public void testType() {
    }

    @Test
    public void testSelectDropDown() {
    }

    @Test
    public void testGetText() {
    }

    @Test
    public void testCloseBrowser() {
    }
}