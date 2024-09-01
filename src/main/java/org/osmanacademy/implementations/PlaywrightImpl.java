package org.osmanacademy.implementations;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.osmanacademy.common.PropertiesFileLoader;
import org.osmanacademy.enums.WebBrowserName;
import org.osmanacademy.interfaces.WebBrowser;
import org.osmanacademy.managers.PlaywrightInstanceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class PlaywrightImpl implements WebBrowser {
    private final Logger logger = LoggerFactory.getLogger(PlaywrightImpl.class);
    private final Properties webBrowserProperties;
    private final PlaywrightInstanceManager playwrightInstanceManager;

    public PlaywrightImpl() {
        this.webBrowserProperties = new PropertiesFileLoader("web-browser.properties").getProperties();
        this.playwrightInstanceManager = new PlaywrightInstanceManager(this.webBrowserProperties);

    }

    @Override
    public void openBrowser() throws Exception {
        logger.info("Trying to open web browser");
        String browserName = this.webBrowserProperties.getProperty("web.browser.name");
        if (StringUtils.isEmpty(browserName)) {
            throw new IllegalStateException("Missing mandatory configuration: 'web.browser.name' in 'web-browser.properties' file.");
        }
        WebBrowserName webBrowserName = validateAndFetchBrowserName(browserName);
        switch (webBrowserName) {
            case CHROME:
                // Implement the logic for opening Chrome
                this.playwrightInstanceManager.openChromeWebBrowser();
                break;
            case EDGE:
                // Implement the logic for opening Edge
                break;
            case FIREFOX:
                // Implement the logic for opening Firefox
                break;
        }
        logger.info("Type of Web Browser Opened: {}", browserName);
    }

    @Override
    public void navigateToURL(String url) throws Exception {
        ObjectUtils.requireNonEmpty(this.playwrightInstanceManager.getPlaywright(), "Please call Open Browser Method before calling this method");
        this.playwrightInstanceManager.getPage().navigate(url);
    }

    @Override
    public void type(String locatorExpression, String webElementName, String testData) throws Exception {
        try {
            logger.info("Trying to enter {}: {}", webElementName, testData);
            this.playwrightInstanceManager.getPage().locator(locatorExpression).fill(testData);
            logger.info("Entered {}: {}", webElementName, testData);
        } catch (Exception e) {
            throw new RuntimeException("Exception Occurred while Trying to Enter " + webElementName + ": " + testData + ": ", e);
        }


    }
    @Override
    public void click(String locatorExpression, String webElementName) throws Exception {
        try {
            logger.info("Trying to click {}", webElementName);
            this.playwrightInstanceManager.getPage().locator(locatorExpression).click();
            logger.info("clicked {}", webElementName);
        } catch (Exception e) {
            throw new RuntimeException("Exception Occurred while Trying to click " + webElementName, e);
        }


    }

    @Override
    public void closeBrowser() throws Exception {
        this.playwrightInstanceManager.close();
    }

    private WebBrowserName validateAndFetchBrowserName(String browserName) throws IllegalArgumentException {
        try {
            return WebBrowserName.findByValue(browserName);
        } catch (IllegalArgumentException e) {
            StringBuilder errorMsg = new StringBuilder();
            errorMsg.append("Invalid Browse Name.\nPossible Cause: " +
                    "\n1) Web Browser Name in the 'web-browser.properties' file should be one following: ");
            for (WebBrowserName wbn : WebBrowserName.values()) {
                errorMsg.append(wbn.getValue()).append(", ");
            }
            logger.error("Exception occurred while trying to open Web Browser", e);
            throw new IllegalArgumentException("%s.".formatted(errorMsg), e);
        }
    }
}