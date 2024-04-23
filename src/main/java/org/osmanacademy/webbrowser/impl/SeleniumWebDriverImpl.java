package org.osmanacademy.webbrowser.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.osmanacademy.webbrowser.WebBrowserAutomation;
import org.osmanacademy.webbrowser.config.AppConfig;
import org.osmanacademy.webbrowser.enums.ExpectedWaitCondition;
import org.osmanacademy.webbrowser.enums.SelectAction;
import org.osmanacademy.webbrowser.exceptions.WebBrowserAutomationException;
import org.osmanacademy.webbrowser.managers.DriverInstanceManager;
import org.osmanacademy.webbrowser.managers.DriverUserActionsManager;
import org.osmanacademy.webbrowser.managers.WaitManager;
import org.osmanacademy.webbrowser.managers.WebElementManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class SeleniumWebDriverImpl implements WebBrowserAutomation {
    private String propertyFileName;
    private Logger logger = LoggerFactory.getLogger(SeleniumWebDriverImpl.class);
    private Duration waitTimeoutSeconds = Duration.ofSeconds(10);
    private WebDriver driver;
    private AppConfig webBrowserProperties;
    private WaitManager waitManager;
    private DriverInstanceManager driverInstanceManager;
    private WebElementManager webElementManager;
    private DriverUserActionsManager driverUserActionsManager;


    public SeleniumWebDriverImpl(String propertyFileName) {
        setPropertyFileName(propertyFileName);
        setWebBrowserProperties(new AppConfig(propertyFileName));
        setDriverInstanceManager(new DriverInstanceManager());
    }

    @Override
    public void openWebBrowser() throws WebBrowserAutomationException {
        try {
            String browserName = getWebBrowserProperties().getProperty("web.browser.name");
            switch (browserName) {
                case "chrome":
                    getDriverInstanceManager().openChromeDriver(getPropertyFileName());
                    break;
                case "edge":
                    getDriverInstanceManager().openEdgeBrowser(getPropertyFileName());
                    break;
                case "firefox":
                    getDriverInstanceManager().openFirefoxDriver(getPropertyFileName());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid browser browserName: '" + browserName + "'. Supported names are: 'chrome', 'firefox', 'edge'.");
            }
            logger.info("Browser is open: {}", browserName);
            setDriver(getDriverInstanceManager().getDriver());
        } catch (WebBrowserAutomationException e) {
            logger.error("Exception occurred when opening the browser", e);
            throw e;
        }
    }

    @Override
    public void minimize() throws WebBrowserAutomationException {
        if (ObjectUtils.isEmpty(getDriver())){
            throw new WebBrowserAutomationException("Web Browser is not open");
        }
        getDriver().manage().window().minimize();
    }

    @Override
    public void maximize() throws WebBrowserAutomationException {
        if (ObjectUtils.isEmpty(getDriver())){
            throw new WebBrowserAutomationException("Web Browser is not open");
        }
        getDriver().manage().window().maximize();

    }

    /**
     * @return
     * @throws WebBrowserAutomationException
     */
    @Override
    public SessionId getSessionID() throws WebBrowserAutomationException {
        if (ObjectUtils.isEmpty(getDriver())){
            throw new WebBrowserAutomationException("Web Browser is not open");
        }
        return ((RemoteWebDriver) getDriver()).getSessionId();
    }

    @Override
    public void refresh() throws WebBrowserAutomationException {
        if (ObjectUtils.isEmpty(getDriver())){
            throw new WebBrowserAutomationException("Web Browser is not open");
        }
        getDriver().navigate().refresh();
    }

    @Override
    public void openNewTab() throws WebBrowserAutomationException {
        if (ObjectUtils.isEmpty(getDriver())){
            throw new WebBrowserAutomationException("Web Browser is not open");
        }
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("window.open()");
    }

    @Override
    public void switchToTabWithTitle(String title) throws WebBrowserAutomationException {
        if (ObjectUtils.isEmpty(getDriver())){
            throw new WebBrowserAutomationException("Web Browser is not open");
        }
        try {
            // Store the original window handle for reference
            String originalWindow = getDriver().getWindowHandle();
            boolean found = false;
            // Iterate over all open tabs
            for (String handle : getDriver().getWindowHandles()) {
                // Switch to each tab
                getDriver().switchTo().window(handle);
                // Check if the tab's title matches the desired title
                if (getDriver().getTitle().equals(title)) {
                    found = true;
                    break;
                }
            }
            // If the tab was not found, switch back to the original window
            if (!found) {
                getDriver().switchTo().window(originalWindow);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void goBack() throws WebBrowserAutomationException {
        if (ObjectUtils.isEmpty(getDriver())){
            throw new WebBrowserAutomationException("Web Browser is not open");
        }
        getDriver().navigate().back();
    }

    @Override
    public void goToUrl(String url) throws WebBrowserAutomationException {
        if (ObjectUtils.isEmpty(getDriver())){
            throw new WebBrowserAutomationException("Web Browser is not open");
        }
        try {
            getDriver().navigate().to(url);
        } catch (Exception e) {
            throw new WebBrowserAutomationException(e.getMessage());
        }
    }

    @Override
    public void goForward() throws WebBrowserAutomationException {
        if (ObjectUtils.isEmpty(getDriver())){
            throw new WebBrowserAutomationException("Web Browser is not open");
        }
        try {
            getDriver().navigate().forward();
        } catch (Exception e) {
            throw new WebBrowserAutomationException(e.getMessage());
        }
    }

    @Override
    public void click(By locator) throws WebBrowserAutomationException {
        if (ObjectUtils.isEmpty(getDriver())){
            throw new WebBrowserAutomationException("Web Browser is not open");
        }
        isElementClickable(locator);
        getDriver().findElement(locator).click();
    }
    @Override
    public void type(By locator, String data) throws WebBrowserAutomationException {
        if (ObjectUtils.isEmpty(getDriver())) {
            throw new WebBrowserAutomationException("Web Browser is not open.");
        }
        setDriverUserActionsManager(new DriverUserActionsManager(getDriver()));
        getDriverUserActionsManager().type(locator,data);
    }

    @Override
    public void type(WebElement webElement, String data) throws WebBrowserAutomationException {
        if (ObjectUtils.isEmpty(getDriver())) {
            throw new WebBrowserAutomationException("Initialize Web Browser before calling this method");
        }
        setWaitManager(new WaitManager(getDriver()));
        getWaitManager().waitFluent(webElement, ExpectedWaitCondition.PRESENCE);
        getWaitManager().waitFluent(webElement, ExpectedWaitCondition.VISIBILE);
        webElement.clear();
        webElement.sendKeys(data);
    }

    @Override
    public void selectDropDown(By locator, SelectAction action, String data) throws WebBrowserAutomationException {
        if (ObjectUtils.isEmpty(getDriver())) {
            throw new WebBrowserAutomationException("Initialize Web Browser before calling this method");
        }
        WebDriverWait wait = new WebDriverWait(this.driver, waitTimeoutSeconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(getDriver().findElement(locator));
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
    public Boolean waitForPageLoad() throws WebBrowserAutomationException {
        return new WebDriverWait(this.driver, waitTimeoutSeconds).until(webDriver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    @Override
    public void closeBrowser() {
        getDriver().quit();
    }

    /**
     * @param elementFromUi
     * @return
     * @throws WebBrowserAutomationException
     */
    @Override
    public List<WebElement> getWebElements(By elementFromUi) throws WebBrowserAutomationException {
        setWebElementManager(new WebElementManager(getDriver()));
        return getWebElementManager().getWebElements(elementFromUi);
    }

    /**
     * @param locator
     * @return
     * @throws WebBrowserAutomationException
     */
    @Override
    public WebElement getWebElement(By locator) throws WebBrowserAutomationException {
        return null;
    }

    /**
     * @param locator
     * @return
     * @throws WebBrowserAutomationException
     */

    /**
     * @param locator
     * @return
     * @throws WebBrowserAutomationException
     */
    @Override
    public Boolean isElementSelected(By locator) throws WebBrowserAutomationException {
        return null;
    }

    /**
     * @param locator
     * @return
     * @throws WebBrowserAutomationException
     */
    @Override
    public String getText(By locator) throws WebBrowserAutomationException {
        return null;
    }

    /**
     * @param locator
     * @param propertyName
     * @return
     * @throws WebBrowserAutomationException
     */
    @Override
    public String getCssValue(By locator, String propertyName) throws WebBrowserAutomationException {
        return null;
    }

    /**
     * @param locator
     * @param name
     * @return
     * @throws WebBrowserAutomationException
     */
    @Override
    public String getAttribute(By locator, String name) throws WebBrowserAutomationException {
        return null;
    }

    /**
     * @param webElement
     * @param name
     * @return
     * @throws WebBrowserAutomationException
     */
    @Override
    public String getAttribute(WebElement webElement, String name) throws WebBrowserAutomationException {
        return null;
    }

    /**
     * @param locator
     * @return
     * @throws WebBrowserAutomationException
     */
    @Override
    public String getTagName(By locator) throws WebBrowserAutomationException {
        return null;
    }

    /**
     * @param locator
     * @throws WebBrowserAutomationException
     */
    @Override
    public void scrollElementIntoView(By locator) throws WebBrowserAutomationException {

    }

    /**
     * @param webElement
     * @throws WebBrowserAutomationException
     */
    @Override
    public void scrollElementIntoView(WebElement webElement) throws WebBrowserAutomationException {

    }

    /**
     * @param locator
     * @return
     * @throws WebBrowserAutomationException
     */
    @Override
    public Object getDataFromListOfAvailableAttributes(By locator) throws WebBrowserAutomationException {
        return null;
    }

    /**
     * @param webElement
     * @return
     * @throws WebBrowserAutomationException
     */
    @Override
    public Object getDataFromListOfAvailableAttributes(WebElement webElement) throws WebBrowserAutomationException {
        return null;
    }


    private void isElementPresent(By locator) {
        getDriver().findElement(locator).isEnabled();
    }


    private void isElementVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(this.driver, waitTimeoutSeconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private void isElementClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(this.driver, waitTimeoutSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void openUrl(String url) throws WebBrowserAutomationException {
        getDriver().navigate().to(url);
    }


    public String getPropertyFileName() {
        return propertyFileName;
    }

    public void setPropertyFileName(String propertyFileName) {
        this.propertyFileName = propertyFileName;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public AppConfig getWebBrowserProperties() {
        return webBrowserProperties;
    }

    public void setWebBrowserProperties(AppConfig webBrowserProperties) {
        this.webBrowserProperties = webBrowserProperties;
    }

    public WaitManager getWaitManager() {
        return waitManager;
    }

    public void setWaitManager(WaitManager waitManager) {
        this.waitManager = waitManager;
    }

    public WebElementManager getWebElementManager() {
        return webElementManager;
    }

    public void setWebElementManager(WebElementManager webElementManager) {
        this.webElementManager = webElementManager;
    }
    public DriverInstanceManager getDriverInstanceManager() {
        return driverInstanceManager;
    }

    public void setDriverInstanceManager(DriverInstanceManager driverInstanceManager) {
        this.driverInstanceManager = driverInstanceManager;
    }

    public DriverUserActionsManager getDriverUserActionsManager() {
        return driverUserActionsManager;
    }

    public void setDriverUserActionsManager(DriverUserActionsManager driverUserActionsManager) {
        this.driverUserActionsManager = driverUserActionsManager;
    }
}