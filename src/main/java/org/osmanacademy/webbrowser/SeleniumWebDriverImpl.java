package org.osmanacademy.webbrowser;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.osmanacademy.common.PropertiesFileLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class SeleniumWebDriverImpl implements WebBrowser {

    private static final Logger logger = LoggerFactory.getLogger(SeleniumWebDriverImpl.class);
    public static final Duration WAIT_TIMEOUT = Duration.ofSeconds(10);
    private WebDriver driver;

    private final PropertiesFileLoader webProperProperties;

    public SeleniumWebDriverImpl() {
        webProperProperties = new PropertiesFileLoader("web-browser.properties");
    }

    @Override
    public void openBrowser() throws Exception {
        try {
            String name = webProperProperties.getProperty("web.browser.name");
            switch (name) {
                case "edge":
                    this.driver = new EdgeDriver();
                    break;
                case "firefox":
                    this.driver = new FirefoxDriver();
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

    public void openChromeDriver() throws Exception {
        try {
            ChromeOptions options = new ChromeOptions();
            if (webProperProperties.getProperty("start.maximized").equals("true")) {
                options.addArguments("--start-maximized");
            }
            this.driver = new ChromeDriver(options);
        } catch (Exception e) {
            logger.error("Exception occurred when opening the chrome browser", e);
            throw e;
        }
    }

    @Override
    public void minimize() {
        try {
            this.driver.manage().window().minimize();
        } catch (Exception e) {
            logger.error("Exception occurred when minimizing the browser", e);
            throw e;
        }

    }

    @Override
    public void maximize() throws Exception {
        try {
            this.driver.manage().window().maximize();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public void refresh() {
        this.driver.navigate().refresh();
    }

    @Override
    public void openNewTab() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.open()");
    }

    @Override
    public void switchToTabWithTitle(String title) throws Exception {
        try {
            // Store the original window handle for reference
            String originalWindow = this.driver.getWindowHandle();
            boolean found = false;
            // Iterate over all open tabs
            for (String handle : this.driver.getWindowHandles()) {
                // Switch to each tab
                this.driver.switchTo().window(handle);
                // Check if the tab's title matches the desired title
                if (this.driver.getTitle().equals(title)) {
                    found = true;
                    break;
                }
            }
            // If the tab was not found, switch back to the original window
            if (!found) {
                this.driver.switchTo().window(originalWindow);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    @Override
    public void goBack() {
        this.driver.navigate().back();
    }

    @Override
    public void goForward() {
        this.driver.navigate().forward();
    }

    @Override
    public void click(By locator) throws Exception {
        isElementClickable(locator);
    }

    @Override
    public void type(By locator) {

    }

    @Override
    public void selectDropDown(By locator) {

    }

    @Override
    public void closeBrowser() {
        this.driver.quit();
    }


    private void isElementPresent(By locator) throws Exception {
        try {
            this.driver.findElement(locator).isEnabled();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    private void isElementVisible(By locator) {

    }

    private void isElementClickable(By locator) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, WAIT_TIMEOUT);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    private void isElementStale(By locator) {

    }


    private void isElementSelected(By locator) {

    }


    private void isElementEnabled(By locator) {

    }

    public void openUrl(String url) throws Exception {
        try {
            this.driver.navigate().to(url);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}