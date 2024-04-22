package org.osmanacademy.applicationundertest.basetest;

import org.osmanacademy.webbrowser.WebBrowserAutomation;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

/**
 * The BaseTestSmoke class represents the base test class for smoke tests. It provides methods for setting up and tearing down test environments, as well as accessing the web browser
 * and website URL.
 */
public class BaseTest {

    private WebBrowserAutomation app;

    private SoftAssert softAssert;

    private String webSiteUrl;

    @BeforeSuite
    public void beforeSuite() {
        // Place your code here
    }



    @AfterSuite
    public void afterSuite() {
        // Place your code here
    }

    public WebBrowserAutomation getApp() {
        return app;
    }

    public void setApp(WebBrowserAutomation app) {
        this.app = app;
    }
    public String getWebSiteUrl() {
        return webSiteUrl;
    }

    public void setWebSiteUrl(String webSiteUrl) {
        this.webSiteUrl = webSiteUrl;
    }

    public SoftAssert getSoftAssert() {
        return softAssert;
    }

    public void setSoftAssert(SoftAssert softAssert) {
        this.softAssert = softAssert;
    }
}
