package org.osmanacademy.webbrowser.managers;


import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.osmanacademy.webbrowser.enums.ExpectedWaitCondition;
import org.osmanacademy.webbrowser.exceptions.WebBrowserAutomationException;

public class WaitManager {

    public static final Duration WAIT_TIMEOUT = Duration.ofSeconds(10);
    private static final long TIMEOUT = 30;
    private WebDriver driver;
    private WebDriverWait waitExplicit;
    private FluentWait<WebDriver> waitFluent;
    private WebElement webElement;
    private WebElementManager webElementManager;

    public WaitManager(WebDriver driver) {
        setDriver(driver);
        setWaitExplicit(new WebDriverWait(getDriver(), WAIT_TIMEOUT));
        setWaitFluent(new FluentWait<WebDriver>(getDriver()).withTimeout(Duration.ofSeconds(TIMEOUT))
                .pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class));
        setWebElementManager(new WebElementManager(driver));

    }


    public void waitExplicit(By webElement, ExpectedWaitCondition condition) throws WebBrowserAutomationException {
        switch (condition) {
            case VISIBILE:
                getWaitExplicit().until(ExpectedConditions.visibilityOf(getDriver().findElement(webElement)));
                break;
            case CLICKABLE:
                getWaitExplicit().until(ExpectedConditions.elementToBeClickable(webElement));
                break;
            case PRESENCE:
                getWaitExplicit().until(ExpectedConditions.presenceOfElementLocated(webElement));
                break;
            default:
                break;
        }
    }

    public void waitFluent(By webElement, ExpectedWaitCondition condition) throws WebBrowserAutomationException {
        try {
            setWebElement(getWebElementManager().getWebElementFromDOM(webElement));
            switch (condition) {
                case VISIBILE:
                    getWaitFluent().until(ExpectedConditions.visibilityOf(getWebElement()));
                    break;
                case CLICKABLE:
                    getWaitFluent().until(ExpectedConditions.elementToBeClickable(webElement));
                    break;
                case PRESENCE:
                    getWaitFluent().until(ExpectedConditions.presenceOfElementLocated(webElement));
                    break;
                case INVISIBILE:
                    getWaitFluent().until(ExpectedConditions.invisibilityOfElementLocated(webElement));
                    break;
                case STALE:
                    getWaitFluent().until(ExpectedConditions.stalenessOf(getWebElement()));
                    break;
                case ENABLED:
                    getWebElement().isEnabled();
                    break;
                default:
                    break;
            }
        } catch (WebBrowserAutomationException e) {
            throw new WebBrowserAutomationException(e.getMessage());
        }
    }

    public void waitFluent(WebElement webElement, ExpectedWaitCondition condition)
            throws WebBrowserAutomationException {
            setWaitFluent(new FluentWait<WebDriver>(getDriver()).withTimeout(Duration.ofSeconds(TIMEOUT))
                    .pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class));
            switch (condition) {
                case VISIBILE:
                    getWaitFluent().until(ExpectedConditions.visibilityOf(webElement));
                    break;
                case CLICKABLE:
                    getWaitFluent().until(ExpectedConditions.elementToBeClickable(webElement));
                    break;
                case STALE:
                    getWaitFluent().until(ExpectedConditions.stalenessOf(webElement));
                    break;
                default:
                    break;
            }
        }

    public void waitFluentOnText(By webElement, String text) throws WebBrowserAutomationException {
        setWaitFluent(new FluentWait<WebDriver>(getDriver()).withTimeout(Duration.ofSeconds(TIMEOUT))
                .pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class));
        getWaitFluent().until(ExpectedConditions.textToBe(webElement, text));

    }

    public void waitForPageLoad() throws WebBrowserAutomationException {
        new WebDriverWait(getDriver(), WAIT_TIMEOUT).until(webDriver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));
    }

    public Boolean waitForPageLoad(By elementToBeStale, By elementToBeVisible) throws WebBrowserAutomationException {
        try {
            waitFluent(elementToBeStale, ExpectedWaitCondition.STALE);
            waitForPageLoad();
            waitFluent(elementToBeVisible, ExpectedWaitCondition.VISIBILE);
            return true;
        } catch (WebBrowserAutomationException e) {
            return false;
        }
    }

    public void waitStatic(long duration) throws WebBrowserAutomationException {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    /**
     * @return the driver
     */
    private WebDriver getDriver() {
        return driver;
    }

    /**
     * @param driver the driver to set
     */
    private void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * @return the wait
     */
    private WebDriverWait getWaitExplicit() {
        return waitExplicit;
    }

    /**
     * @param wait the wait to set
     */
    private void setWaitExplicit(WebDriverWait wait) {
        this.waitExplicit = wait;
    }

    /**
     * @return the webElement
     */
    private WebElement getWebElement() {
        return webElement;
    }

    /**
     * @param webElement the webElement to set
     */
    private void setWebElement(WebElement webElement) {
        this.webElement = webElement;
    }

    /**
     * @return the waitFluent
     */
    public FluentWait<WebDriver> getWaitFluent() {
        return waitFluent;
    }

    /**
     * @param waitFluent the waitFluent to set
     */
    public void setWaitFluent(FluentWait<WebDriver> waitFluent) {
        this.waitFluent = waitFluent;
    }

    public WebElementManager getWebElementManager() {
        return webElementManager;
    }

    public void setWebElementManager(WebElementManager webElementManager) {
        this.webElementManager = webElementManager;
    }

}

