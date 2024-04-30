package org.osmanacademy.interfaces;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.SessionId;
import org.osmanacademy.enums.SelectAction;

import java.util.List;

/**
 * The WebBrowser interface represents a web browser and provides methods to interact with it.
 */
public interface WebBrowser {

    void openBrowser() throws Exception;

    void minimize() throws Exception;

    void maximize() throws Exception;

   public SessionId getSessionID() throws Exception;

    void openUrl(String url) throws Exception;

    void refresh() throws Exception;

    void openNewTab() throws Exception;

    void switchToTabWithTitle(String title) throws Exception;

    void goBack() throws Exception;

    void goForward() throws Exception;

    void goToUrl(String url) throws Exception;

    void click(By locator) throws Exception;

    void click(WebElement webElement);

    void type(By locator, String data) throws Exception;

    void type(WebElement webElement, String data) throws Exception;

    void selectDropDown(By locator, SelectAction action, String data) throws Exception;

    public Boolean waitForPageLoad() throws Exception;

    void closeBrowser() throws Exception;

    public List<WebElement> getWebElements(By locator) throws Exception;

    public WebElement getWebElement(By locator) throws Exception;

    public Boolean isElementDisplayed(By locator) throws Exception;

    public Boolean isElementDisplayed(WebElement webElement) throws Exception;

    public Boolean isElementEnabled(By locator) throws Exception;

    public Boolean isElementSelected(By locator) throws Exception;

    public String getText(By locator) throws Exception;

    public String getCssValue(By locator, String propertyName) throws Exception;

    public String getAttribute(By locator, String name) throws Exception;

    public String getAttribute(WebElement webElement, String name) throws Exception;

    public String getTagName(By locator) throws Exception;

    public void scrollElementIntoView(By locator) throws Exception;

    public void scrollElementIntoView(WebElement webElement) throws Exception;


    public Object getDataFromListOfAvailableAttributes(By locator) throws Exception;

    public Object getDataFromListOfAvailableAttributes(WebElement webElement) throws Exception;

}
