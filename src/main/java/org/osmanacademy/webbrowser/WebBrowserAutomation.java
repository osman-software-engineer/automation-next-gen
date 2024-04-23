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

    void openWebBrowser() throws WebBrowserAutomationException;

    void minimize() throws WebBrowserAutomationException;

    void maximize() throws WebBrowserAutomationException;

   SessionId getSessionID() throws WebBrowserAutomationException;

    void openUrl(String url) throws WebBrowserAutomationException;

    void refresh() throws WebBrowserAutomationException;

    void openNewTab() throws WebBrowserAutomationException;

    void switchToTabWithTitle(String title) throws WebBrowserAutomationException;

    void goBack() throws WebBrowserAutomationException;

    void goForward() throws WebBrowserAutomationException;

    void goToUrl(String url) throws Exception;

    void click(By locator) throws WebBrowserAutomationException;

    void type(By locator, String data) throws WebBrowserAutomationException;

    void type(WebElement webElement, String data) throws WebBrowserAutomationException;

    void selectDropDown(By locator, SelectAction action, String data) throws WebBrowserAutomationException;

    Boolean waitForPageLoad() throws WebBrowserAutomationException;

    void closeBrowser() throws WebBrowserAutomationException;

    List<WebElement> getWebElements(By locator) throws WebBrowserAutomationException;

    WebElement getWebElement(By locator) throws WebBrowserAutomationException;

    Boolean isElementSelected(By locator) throws WebBrowserAutomationException;

    String getText(By locator) throws WebBrowserAutomationException;

    String getCssValue(By locator, String propertyName) throws WebBrowserAutomationException;

    String getAttribute(By locator, String name) throws WebBrowserAutomationException;

    String getAttribute(WebElement webElement, String name) throws WebBrowserAutomationException;

    String getTagName(By locator) throws WebBrowserAutomationException;

    void scrollElementIntoView(By locator) throws WebBrowserAutomationException;

    void scrollElementIntoView(WebElement webElement) throws WebBrowserAutomationException;

    Object getDataFromListOfAvailableAttributes(By locator) throws WebBrowserAutomationException;

    Object getDataFromListOfAvailableAttributes(WebElement webElement) throws WebBrowserAutomationException;


}
