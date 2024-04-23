package org.osmanacademy.webbrowser.managers;

import org.apache.commons.lang3.ObjectUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.osmanacademy.webbrowser.enums.ExpectedWaitCondition;
import org.osmanacademy.webbrowser.exceptions.WebBrowserAutomationException;

import java.time.Duration;

public class DriverUserActionsManager {
    private Duration waitTimeoutSeconds = Duration.ofSeconds(10);
    private WebDriver driver;
    private WebElementManager webElementManager;
    private WaitManager waitManager;
    public DriverUserActionsManager(WebDriver driver) {
        setDriver(driver);
        setWaitManager(new WaitManager(driver));
        setWebElementManager(new WebElementManager(driver));
    }
    public void click(By locator) throws WebBrowserAutomationException {
        if (ObjectUtils.isEmpty(getDriver())){
            throw new WebBrowserAutomationException("Web Browser is not open");
        }
        getDriver().findElement(locator).click();
    }
    public void type(By locator, String data) throws WebBrowserAutomationException {
        if (ObjectUtils.isEmpty(getDriver())) {
            throw new WebBrowserAutomationException("Web Browser is not open.");
        }
        getWaitManager().waitFluent(locator, ExpectedWaitCondition.PRESENCE);
        getWaitManager().waitFluent(locator, ExpectedWaitCondition.VISIBILE);
        getWebElementManager().getWebElementFromDOM(locator);
        getWebElementManager().getWebElementFromMemory().clear();
        getWebElementManager().getWebElementFromMemory().sendKeys(data);
    }
    private void isElementClickable(By locator) throws WebBrowserAutomationException {
        getWaitManager().waitExplicit(locator,ExpectedWaitCondition.CLICKABLE);
    }

    public Boolean isElementDisplayed(By locator) throws WebBrowserAutomationException {
        return getDriver().findElement(locator).isDisplayed();
    }

    /**
     * @param locator
     * @return
     * @throws WebBrowserAutomationException
     */
    public Boolean isElementEnabled(By locator) throws WebBrowserAutomationException {
        return getDriver().findElement(locator).isEnabled();
    }

    private WebElementManager getWebElementManager() {
        return webElementManager;
    }
    public void setWebElementManager(WebElementManager webElementManager) {
        this.webElementManager = webElementManager;
    }
    public WaitManager getWaitManager() {
        return waitManager;
    }
    private void setWaitManager(WaitManager waitManager) {
        this.waitManager = waitManager;
    }

    private WebDriver getDriver() {
        return driver;
    }

    private void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}
