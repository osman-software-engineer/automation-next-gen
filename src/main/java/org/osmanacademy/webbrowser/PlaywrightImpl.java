package org.osmanacademy.webbrowser;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import org.openqa.selenium.*;
import org.osmanacademy.common.PropertiesFileLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class PlaywrightImpl implements WebBrowser {

    private static final Logger logger = LoggerFactory.getLogger(SeleniumWebDriverImpl.class);
    public static final Duration WAIT_TIMEOUT = Duration.ofSeconds(10);
    private Browser driver;

    private final PropertiesFileLoader webProperProperties;

    public PlaywrightImpl() {
        webProperProperties = new PropertiesFileLoader("web-browser.properties");
    }

    @Override
    public void openBrowser() throws Exception {
        try {
            String name = webProperProperties.getProperty("web.browser.name");
            switch (name) {
                case "edge":

                    break;
                case "firefox":

                    break;
                default:
                    openChromeDriver();

            }
            logger.info("Browser is open: {}", name);
        } catch (Exception e) {
            logger.error("Exception occurred when opening the browser", e);
            throw e;
        }
    }

    @Override
    public void minimize() {

    }

    @Override
    public void maximize() throws Exception {

    }

    @Override
    public void openUrl(String url) throws Exception {

    }

    @Override
    public void refresh() {

    }

    @Override
    public void openNewTab() {

    }

    @Override
    public void switchToTabWithTitle(String title) throws Exception {

    }

    @Override
    public void goBack() {

    }

    @Override
    public void goForward() {

    }

    @Override
    public void click(By locator) throws Exception {

    }

    @Override
    public void type(By locator,String data) {

    }

    @Override
    public void selectDropDown(By locator, SelectAction action, String data) throws Exception {

    }

    @Override
    public void closeBrowser() {

    }
    public void openChromeDriver() throws Exception {
        try(Playwright playwright = Playwright.create()) {
            BrowserType browserType = playwright.chromium();
            BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
            options.setHeadless(false);
            browserType.launch(options).newPage();
        } catch (Exception e) {
            logger.error("Exception occurred when opening the chrome browser", e);
            throw e;
        }
    }

    public static void main(String[] args) throws Exception {
        PlaywrightImpl unitTest = new PlaywrightImpl();
        unitTest.openBrowser();
    }
}