package org.osmanacademy.webbrowser;

import org.openqa.selenium.By;
import org.osmanacademy.webbrowser.enums.SelectAction;
import org.osmanacademy.webbrowser.exceptions.WebBrowserException;

/**
 * The WebBrowser interface represents a web browser and provides methods to interact with it.
 */
public interface WebBrowserAutomation {

    void openBrowser() throws WebBrowserException;

    void minimize() throws WebBrowserException;

    void maximize() throws  WebBrowserException;

    void openUrl(String url) throws WebBrowserException;

    void refresh() throws WebBrowserException;

    void openNewTab() throws WebBrowserException;

    void switchToTabWithTitle(String title) throws WebBrowserException;

    void goBack() throws WebBrowserException;

    void goForward() throws WebBrowserException;

    void click(By locator) throws WebBrowserException;

    void type(By locator, String data) throws WebBrowserException;

    void selectDropDown(By locator, SelectAction action, String data) throws WebBrowserException;

    void closeBrowser() throws WebBrowserException;


}
