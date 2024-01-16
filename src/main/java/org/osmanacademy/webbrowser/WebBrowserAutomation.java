package org.osmanacademy.webbrowser;

import org.openqa.selenium.WebElement;

public interface WebBrowserAutomation {

    void openBrowser();

    void minimize();

    void maximize();

    void refresh();

    void openNewTab();

    void closeTab(CloseAction  action);

    void goBack();

    void goForward();

    void openUrl(String url);

    void click(WebElement webElement);

    void type(WebElement webElement);

    void selectDropDown(WebElement webElement);

    void getText(WebElement webElement);

    void closeBrowser();


}
