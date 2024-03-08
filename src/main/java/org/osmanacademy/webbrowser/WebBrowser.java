package org.osmanacademy.webbrowser;

import org.openqa.selenium.By;

public interface WebBrowser {

    void openBrowser() throws Exception;

    void minimize();

    void maximize() throws Exception;

    void openUrl(String url) throws Exception;

    void refresh();

    void openNewTab();

    void switchToTabWithTitle(String title) throws Exception;

    void goBack();

    void goForward();

    void click(By locator) throws Exception;

    void type(By locator);

    void selectDropDown(By locator);

    void closeBrowser();


}
