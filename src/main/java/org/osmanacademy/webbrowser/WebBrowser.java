package org.osmanacademy.webbrowser;

import org.openqa.selenium.By;

public interface WebBrowser {

    void openBrowser() throws Exception;

    void minimize() throws Exception;

    void maximize() throws Exception;

    void openUrl(String url) throws Exception;

    void refresh() throws Exception;

    void openNewTab() throws Exception;

    void switchToTabWithTitle(String title) throws Exception;

    void goBack() throws Exception;

    void goForward() throws Exception;

    void click(By locator) throws Exception;

    void type(By locator, String data) throws Exception;

    void selectDropDown(By locator, SelectAction action, String data) throws Exception;

    void closeBrowser() throws Exception;


}
