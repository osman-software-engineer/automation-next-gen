package org.osmanacademy.implementations;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.osmanacademy.common.PropertiesFileLoader;
import org.osmanacademy.enums.WebBrowserName;
import org.osmanacademy.interfaces.WebBrowser;
import org.osmanacademy.enums.ExpectedWaitCondition;
import org.osmanacademy.enums.SelectAction;
import org.osmanacademy.managers.DriverInstanceManager;
import org.osmanacademy.managers.WaitManager;
import org.osmanacademy.managers.WebElementManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class SeleniumWebDriverImpl implements WebBrowser {
    public String propertyFileName;
    private final Logger logger = LoggerFactory.getLogger(SeleniumWebDriverImpl.class);
    private Duration WAIT_TIMEOUT = Duration.ofSeconds(10);
    private WebDriver driver;
    private PropertiesFileLoader webBrowserProperties;
    private WaitManager waitManager;
    private DriverInstanceManager driverManager;
    private WebElementManager webElementManager;

    public SeleniumWebDriverImpl(String propertyFileName) {
        setPropertyFileName(propertyFileName);
        setWebBrowserProperties(new PropertiesFileLoader(propertyFileName));
        setDriverManager(new DriverInstanceManager());
    }

    @Override
    public void openBrowser() throws Exception {
        WebBrowserName webBrowserName;
        String browserName = getWebBrowserProperties().getProperty("web.browser.name");
        // Check for Valid request
        try {
            webBrowserName = WebBrowserName.findByValue(browserName);
        } catch (IllegalArgumentException e) {
            {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid Test Execution Environment Name.\nPossible Causes. " +
                        "\n1) Web Browser Name in the 'web-browser.properties' file should be one of the following: ");
                for (WebBrowserName wbn : WebBrowserName.values()) {
                    sb.append(wbn.getValue()).append(", ");
                }
                if (!sb.isEmpty()) {
                    sb.setLength(sb.length() - 2);
                }
                logger.error("Exception occurred when opening the browser", e);
                throw new IllegalArgumentException(sb.toString()+".", e);
            }
        }
        // Main Logic for handling open browser request
        switch (webBrowserName) {
            case CHROME:
                getDriverManager().openChromeDriver(getPropertyFileName());
                break;
            case EDGE:
                getDriverManager().openEdgeBrowser(getPropertyFileName());
                break;
            case FIREFOX:
                getDriverManager().openFirefoxDriver(getPropertyFileName());
                break;
        }
        logger.info("Type of Web Browser Opened: {}", browserName);
        setDriver(getDriverManager().getDecorated());
    }

    @Override
    public void minimize() throws Exception {
        if (getDriver() == null){
            throw new Exception("Web Browser is not open");
        }
        getDriver().manage().window().minimize();
    }

    @Override
    public void maximize() throws Exception {
        if (getDriver() == null){
            throw new Exception("Web Browser is not open");
        }
        getDriver().manage().window().maximize();

    }

    /**
     * @return
     * @throws Exception
     */
    @Override
    public SessionId getSessionID() throws Exception {
        if (getDriver() == null){
            throw new Exception("Web Browser is not open");
        }
        return ((RemoteWebDriver) getDriver()).getSessionId();
    }

    @Override
    public void refresh() throws Exception {
        if (getDriver() == null){
            throw new Exception("Web Browser is not open");
        }
        getDriver().navigate().refresh();
    }

    @Override
    public void openNewTab() throws Exception {
        if (getDriver() == null){
            throw new Exception("Web Browser is not open");
        }
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("window.open()");
    }

    @Override
    public void switchToTabWithTitle(String title) throws Exception {
        if (getDriver() == null){
            throw new Exception("Web Browser is not open");
        }
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
    }

    @Override
    public void goBack() {
        getDriver().navigate().back();
    }

    @Override
    public void goToUrl(String url) throws Exception {
        getDriver().navigate().to(url);
    }

    @Override
    public void goForward() throws Exception {
        getDriver().navigate().forward();
    }

    @Override
    public void click(By locator) {
        isElementClickable(locator);
        getDriver().findElement(locator).click();
    }

    @Override
    public void click(WebElement webElement) {
        isElementClickable(webElement);
        webElement.click();
    }

    @Override
    public void type(By locator, String data) throws Exception {
        if (getDriver() == null) {
            throw new Exception("Initialize Web Browser before calling this method");
        }
        try {
            setWaitManager(new WaitManager(getDriver()));
            getWaitManager().waitFluent(locator, ExpectedWaitCondition.PRESENCE);
            getWaitManager().waitFluent(locator, ExpectedWaitCondition.VISIBILE);
            setWebElementManager(new WebElementManager(getDriver()));
            getWebElementManager().getWebElementFromDOM(locator);
            getWebElementManager().getWebElementFromMemory().clear();
            getWebElementManager().getWebElementFromMemory().sendKeys(data);
        } catch (Exception e) {
            throw new Exception("Couldn't able to type: " , e);
        }
    }

    @Override
    public void type(WebElement webElement, String data) throws Exception {
        if (getDriver() == null) {
            throw new Exception("Initialize Web Browser before calling this method");
        }
        setWaitManager(new WaitManager(getDriver()));
        getWaitManager().waitFluent(webElement, ExpectedWaitCondition.PRESENCE);
        getWaitManager().waitFluent(webElement, ExpectedWaitCondition.VISIBILE);
        webElement.clear();
        webElement.sendKeys(data);
    }

    @Override
    public void selectDropDown(By locator, SelectAction action, String data) throws Exception {
        if (getDriver() == null) {
            throw new Exception("Initialize Web Browser before calling this method");
        }
        WebDriverWait wait = new WebDriverWait(this.driver, WAIT_TIMEOUT);
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
    public Boolean waitForPageLoad() throws Exception {
        return new WebDriverWait(this.driver, WAIT_TIMEOUT).until(webDriver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    @Override
    public void closeBrowser() {
        getDriver().quit();
    }

    /**
     * @param elementFromUi
     * @return
     * @throws Exception
     */
    @Override
    public List<WebElement> getWebElements(By elementFromUi) throws Exception {
        setWebElementManager(new WebElementManager(getDriver()));
        return getWebElementManager().getWebElements(elementFromUi);
    }

    /**
     * @param locator
     * @return
     * @throws Exception
     */
    @Override
    public WebElement getWebElement(By locator) throws Exception {
        return null;
    }

    /**
     * @param locator
     * @return
     * @throws Exception
     */
    @Override
    public Boolean isElementDisplayed(By locator) throws Exception {
        return null;
    }

    /**
     * @param webElement
     * @return
     * @throws Exception
     */
    @Override
    public Boolean isElementDisplayed(WebElement webElement) throws Exception {
        return null;
    }

    /**
     * @param locator
     * @return
     * @throws Exception
     */
    @Override
    public Boolean isElementEnabled(By locator) throws Exception {
        return null;
    }

    /**
     * @param locator
     * @return
     * @throws Exception
     */
    @Override
    public Boolean isElementSelected(By locator) throws Exception {
        return null;
    }

    /**
     * @param locator
     * @return
     * @throws Exception
     */
    @Override
    public String getText(By locator) throws Exception {
        return null;
    }

    /**
     * @param locator
     * @param propertyName
     * @return
     * @throws Exception
     */
    @Override
    public String getCssValue(By locator, String propertyName) throws Exception {
        return null;
    }

    /**
     * @param locator
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public String getAttribute(By locator, String name) throws Exception {
        return null;
    }

    /**
     * @param webElement
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public String getAttribute(WebElement webElement, String name) throws Exception {
        return null;
    }

    /**
     * @param locator
     * @return
     * @throws Exception
     */
    @Override
    public String getTagName(By locator) throws Exception {
        return null;
    }

    /**
     * @param locator
     * @throws Exception
     */
    @Override
    public void scrollElementIntoView(By locator) throws Exception {

    }

    /**
     * @param webElement
     * @throws Exception
     */
    @Override
    public void scrollElementIntoView(WebElement webElement) throws Exception {

    }

    /**
     * @param locator
     * @return
     * @throws Exception
     */
    @Override
    public Object getDataFromListOfAvailableAttributes(By locator) throws Exception {
        return null;
    }

    /**
     * @param webElement
     * @return
     * @throws Exception
     */
    @Override
    public Object getDataFromListOfAvailableAttributes(WebElement webElement) throws Exception {
        return null;
    }


    private void isElementPresent(By locator) {
        getDriver().findElement(locator).isEnabled();
    }


    private void isElementVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(this.driver, WAIT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private void isElementClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(this.driver, WAIT_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }


    private void isElementClickable(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(this.driver, WAIT_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    @Override
    public void openUrl(String url) throws Exception {
        getDriver().navigate().to(url);
    }


    private String getPropertyFileName() {
        return propertyFileName;
    }

    private void setPropertyFileName(String propertyFileName) {
        this.propertyFileName = propertyFileName;
    }

    private WebDriver getDriver() {
        return driver;
    }

    private void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    private PropertiesFileLoader getWebBrowserProperties() {
        return webBrowserProperties;
    }

    private void setWebBrowserProperties(PropertiesFileLoader webBrowserProperties) {
        this.webBrowserProperties = webBrowserProperties;
    }

    private WaitManager getWaitManager() {
        return waitManager;
    }

    private void setWaitManager(WaitManager waitManager) {
        this.waitManager = waitManager;
    }

    private DriverInstanceManager getDriverManager() {
        return driverManager;
    }

    private void setDriverManager(DriverInstanceManager driverManager) {
        this.driverManager = driverManager;
    }

    private WebElementManager getWebElementManager() {
        return webElementManager;
    }

    private void setWebElementManager(WebElementManager webElementManager) {
        this.webElementManager = webElementManager;
    }
}