package org.osmanacademy.webbrowser;

import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.By;

public interface WebBrowser {

    SelfHealingDriver openBrowser() throws Exception;

    void minimize();

    void maximize();

    void openUrl(String url) throws Exception;

    void refresh();

    void openNewTab();

    boolean switchToTabWithTitle(String title) throws Exception;

    void goBack();

    void goForward();


    void click(By locator) throws Exception;

    void type(By locator);

    void selectDropDown(By locator);

    String getText(By locator);

    void closeBrowser();

}
