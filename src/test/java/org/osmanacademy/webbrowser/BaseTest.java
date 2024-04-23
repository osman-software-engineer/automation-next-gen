package org.osmanacademy.webbrowser;

import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

/**
 * The BaseTestSmoke class represents the base test class for smoke tests. It provides methods for setting up and tearing down test environments, as well as accessing the web browser
 * and website URL.
 */
public class BaseTest {

    private WebBrowserAutomation webBrowser;

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

    public WebBrowserAutomation getWebBrowser() {
        return webBrowser;
    }

    public void setWebBrowser(WebBrowserAutomation webBrowser) {
        this.webBrowser = webBrowser;
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
