package org.osmanacademy.interfaces;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.SessionId;
import org.osmanacademy.enums.SelectAction;
import org.osmanacademy.exceptions.AutomationNextGenException;

import java.util.List;

/**
 * The WebBrowser interface represents a web browser and provides methods to interact with it.
 */
public interface WebBrowser {

    void openBrowser() throws AutomationNextGenException;

    void minimize() throws AutomationNextGenException;

    void maximize() throws AutomationNextGenException;

   public SessionId getSessionID() throws AutomationNextGenException;

    void openUrl(String url) throws AutomationNextGenException;

    void refresh() throws AutomationNextGenException;

    void openNewTab() throws AutomationNextGenException;

    void switchToTabWithTitle(String title) throws AutomationNextGenException;

    void goBack() throws AutomationNextGenException;

    void goForward() throws AutomationNextGenException;

    void goToUrl(String url) throws Exception;

    void click(By locator) throws AutomationNextGenException;

    void click(WebElement webElement);

    void type(By locator, String data) throws AutomationNextGenException;

    void type(WebElement webElement, String data) throws AutomationNextGenException;

    void selectDropDown(By locator, SelectAction action, String data) throws AutomationNextGenException;

    public Boolean waitForPageLoad() throws AutomationNextGenException;

    void closeBrowser() throws AutomationNextGenException;

    public List<WebElement> getWebElements(By locator) throws AutomationNextGenException;

    public WebElement getWebElement(By locator) throws AutomationNextGenException;

    public Boolean isElementDisplayed(By locator) throws AutomationNextGenException;

    public Boolean isElementDisplayed(WebElement webElement) throws AutomationNextGenException;

    public Boolean isElementEnabled(By locator) throws AutomationNextGenException;

    public Boolean isElementSelected(By locator) throws AutomationNextGenException;

    public String getText(By locator) throws AutomationNextGenException;

    public String getCssValue(By locator, String propertyName) throws AutomationNextGenException;

    public String getAttribute(By locator, String name) throws AutomationNextGenException;

    public String getAttribute(WebElement webElement, String name) throws AutomationNextGenException;

    public String getTagName(By locator) throws AutomationNextGenException;

    public void scrollElementIntoView(By locator) throws AutomationNextGenException;

    public void scrollElementIntoView(WebElement webElement) throws AutomationNextGenException;


    public Object getDataFromListOfAvailableAttributes(By locator) throws AutomationNextGenException;

    public Object getDataFromListOfAvailableAttributes(WebElement webElement) throws AutomationNextGenException;

}
