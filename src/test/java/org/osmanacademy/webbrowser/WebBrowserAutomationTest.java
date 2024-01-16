package org.osmanacademy.webbrowser;

import org.testng.annotations.*;

import static org.testng.Assert.*;

public class WebBrowserAutomationTest {
    protected WebBrowserAutomation webBrowserAutomation;
    @BeforeClass
    public void setUp() {
        webBrowserAutomation = new WebBrowserAutomationImpl(WebBrowserType.CHROME);
    }

    @AfterClass
    public void tearDown() {
        try {
            webBrowserAutomation.closeBrowser();
        } catch (Exception e) {
            fail("Exception was thrown during the closeBrowser method execution: " + e.getMessage());
        }
    }

    @Test(priority=1)
    public void testOpenBrowser() {
        try {
            webBrowserAutomation.openBrowser();
        } catch (Exception e) {
            fail("Exception was thrown during the openBrowser method execution: " + e.getMessage());
        }
    }

    @Test(priority=2)
    public void testMaximize() {
        try {
            webBrowserAutomation.maximize();
        } catch (Exception e) {
            fail("Exception was thrown during the testMaximize method execution: " + e.getMessage());
        }
    }

    @Test(priority=2)
    public void testMinimize() {
        try {
            webBrowserAutomation.minimize();
        } catch (Exception e) {
            fail("Exception was thrown during the testMaximize method execution: " + e.getMessage());
        }
    }

}