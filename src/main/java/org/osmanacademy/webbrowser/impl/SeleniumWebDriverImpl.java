package org.osmanacademy.webbrowser.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.osmanacademy.webbrowser.WebBrowserAutomation;
import org.osmanacademy.webbrowser.config.AppConfig;
import org.osmanacademy.webbrowser.enums.SelectAction;
import org.osmanacademy.webbrowser.exceptions.WebBrowserException;
import org.osmanacademy.webbrowser.utils.ChromeOptionsManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class SeleniumWebDriverImpl implements WebBrowserAutomation {


    private static final Logger logger = LoggerFactory.getLogger(SeleniumWebDriverImpl.class);
    public static final Duration WAIT_TIMEOUT = Duration.ofSeconds(10);
    private WebDriver driver;
    private final AppConfig webBrowserProperties;

    public SeleniumWebDriverImpl() {
        webBrowserProperties = new AppConfig("webbrowser.properties");
    }

    @Override
    public void openBrowser() throws WebBrowserException {
        try {
            String browserName = webBrowserProperties.getProperty("web.browser.name");
            switch (browserName) {
                case "chrome":
                    openChromeDriver();
                    break;
                case "edge":
                    this.driver = new EdgeDriver();
                    break;
                case "firefox":
                    this.driver = new FirefoxDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid browser browserName: '" + browserName
                            + "'. Supported names are: 'chrome', 'firefox', 'edge'.");
            }
            logger.info("Browser is open: {}", browserName);
        } catch (WebBrowserException e) {
            logger.error("Exception occurred when opening the browser", e);
            throw e;
        }
    }

    /**
     * Opens a ChromeDriver instance with options provided by the ChromeOptionsManager.
     * If the 'start.maximized' property is set to true in the webBrowserProperties, the ChromeDriver will start maximized.
     * If the 'mobile.emulation' property is set to true in the webBrowserProperties, the ChromeDriver will emulate a mobile device.
     * The 'mobile.device.name' property should contain the name of the desired mobile device to be emulated.
     *
     * @throws WebBrowserException if there was an error opening the ChromeDriver or if the device name for mobile emulation is invalid.
     */
    private void openChromeDriver() throws WebBrowserException {
        ChromeOptionsManager chromeOptionsManager = new ChromeOptionsManager();
        chromeOptionsManager.manageStartMaximized();
        chromeOptionsManager.manageMobileEmulation();
        this.driver = new ChromeDriver(chromeOptionsManager.getChromeOptions());
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
    public void maximize() throws WebBrowserException {
        try {
            this.driver.manage().window().maximize();
        } catch (Exception e) {
            throw new WebBrowserException(e.getMessage());
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
    public void switchToTabWithTitle(String title) {
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
    public void click(By locator) {
        isElementClickable(locator);
        isElementVisible(locator);
        isElementEnabled(locator);
        this.driver.findElement(locator).click();
    }

    @Override
    public void type(By locator, String data) {
        WebDriverWait wait = new WebDriverWait(this.driver, WAIT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        this.driver.findElement(locator).clear();
        this.driver.findElement(locator).sendKeys(data);
    }

    @Override
    public void selectDropDown(By locator, SelectAction action, String data) {
        WebDriverWait wait = new WebDriverWait(this.driver, WAIT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(this.driver.findElement(locator));
        switch (action) {
            case BY_INDEX:
                select.selectByIndex(Integer.parseInt(data));
                break;
            case BY_VISIBLE_TEXT:
                select.selectByVisibleText(data);
                break;
            case BY_VALUE:
                select.selectByValue(data);
                break;
        }
    }

    @Override
    public void closeBrowser() {
        this.driver.quit();
    }


    private void isElementPresent(By locator) {
        this.driver.findElement(locator).isEnabled();
    }


    private void isElementVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(this.driver, WAIT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private void isElementClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(this.driver, WAIT_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }


    private void isElementStale(By locator) {

    }


    private void isElementSelected(By locator) {
        this.driver.findElement(locator).isSelected();
    }


    private void isElementEnabled(By locator) {
        this.driver.findElement(locator).isEnabled();

    }

    public void openUrl(String url) throws WebBrowserException {
        this.driver.navigate().to(url);
    }
}