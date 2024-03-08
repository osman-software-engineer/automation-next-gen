package org.osmanacademy.webbrowser;


import org.testng.annotations.Test;

import static org.testng.Assert.fail;

public class WebBrowserTest {

    @Test
    public void testOpenBrowser() {
        try {
            WebBrowser app = new SeleniumWebDriverImpl();
            app.openBrowser();
            app.closeBrowser();
        } catch (Exception e) {
            fail("Exception was thrown during the openBrowser method execution: " + e.getMessage());
        }
    }

    @Test
    public void testMaximize() {
        try {
            WebBrowser app = new SeleniumWebDriverImpl();
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
            WebBrowser app = new SeleniumWebDriverImpl();
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
            WebBrowser app = new SeleniumWebDriverImpl();
            app.openBrowser();
            app.maximize();
            app.openUrl("https://www.amazon.in/");
            app.closeBrowser();
        } catch (Exception e) {
            fail("Exception was thrown during the testOpenUrl method execution: " + e.getMessage());
        }
    }

    @Test
    public void testRefresh() {
        try {
            WebBrowser app = new SeleniumWebDriverImpl();
            app.openBrowser();
            app.maximize();
            app.openUrl("https://www.amazon.in/");
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
            WebBrowser app = new SeleniumWebDriverImpl();
            app.openBrowser();
            app.maximize();
            app.openUrl("https://www.amazon.in/");
            app.openNewTab();
            app.switchToTabWithTitle("New Tab");
        } catch (Exception e) {
            fail("Exception was thrown during the testOpenNewTab method execution: " + e.getMessage());
        }
    }

    @Test
    public void testSwitchToTabWithTitle() {
        try {

        } catch (Exception e) {
            fail("Exception was thrown during the testSwitchToTabWithTitle method execution: " + e.getMessage());
        }
    }

    @Test
    public void testGoBack() {
    }

    @Test
    public void testGoForward() {
    }


    @Test
    public void testClick() {
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