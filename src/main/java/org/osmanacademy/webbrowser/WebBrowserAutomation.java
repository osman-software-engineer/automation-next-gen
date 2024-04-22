package org.osmanacademy.webbrowser;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.SessionId;
import org.osmanacademy.webbrowser.enums.SelectAction;
import org.osmanacademy.webbrowser.exceptions.WebBrowserAutomationException;

import java.util.List;

/**
 * The WebBrowser interface represents a web browser and provides methods to interact with it.
 */
public interface WebBrowserAutomation {

    WebDriver openBrowser() throws WebBrowserAutomationException;

    void minimize() throws WebBrowserAutomationException;

    void maximize() throws WebBrowserAutomationException;

   public SessionId getSessionID() throws WebBrowserAutomationException;

    void openUrl(String url) throws WebBrowserAutomationException;

    void refresh() throws WebBrowserAutomationException;

    void openNewTab() throws WebBrowserAutomationException;

    void switchToTabWithTitle(String title) throws WebBrowserAutomationException;

    void goBack() throws WebBrowserAutomationException;

    void goForward() throws WebBrowserAutomationException;

    void goToUrl(String url) throws Exception;

    void click(By locator) throws WebBrowserAutomationException;

    void click(WebElement webElement);

    void type(By locator, String data) throws WebBrowserAutomationException;

    void type(WebElement webElement, String data) throws WebBrowserAutomationException;

    void selectDropDown(By locator, SelectAction action, String data) throws WebBrowserAutomationException;

    public Boolean waitForPageLoad() throws WebBrowserAutomationException;

    void closeBrowser() throws WebBrowserAutomationException;

    public List<WebElement> getWebElements(By locator) throws WebBrowserAutomationException;

    public WebElement getWebElement(By locator) throws WebBrowserAutomationException;

    public Boolean isElementDisplayed(By locator) throws WebBrowserAutomationException;

    public Boolean isElementDisplayed(WebElement webElement) throws WebBrowserAutomationException;

    public Boolean isElementEnabled(By locator) throws WebBrowserAutomationException;

    public Boolean isElementSelected(By locator) throws WebBrowserAutomationException;

    public String getText(By locator) throws WebBrowserAutomationException;

    public String getCssValue(By locator, String propertyName) throws WebBrowserAutomationException;

    public String getAttribute(By locator, String name) throws WebBrowserAutomationException;

    public String getAttribute(WebElement webElement, String name) throws WebBrowserAutomationException;

    public String getTagName(By locator) throws WebBrowserAutomationException;

    public void scrollElementIntoView(By locator) throws WebBrowserAutomationException;

    public void scrollElementIntoView(WebElement webElement) throws WebBrowserAutomationException;

    public Object getDataFromListOfAvailableAttributes(By locator) throws WebBrowserAutomationException;

    public Object getDataFromListOfAvailableAttributes(WebElement webElement) throws WebBrowserAutomationException;


}
