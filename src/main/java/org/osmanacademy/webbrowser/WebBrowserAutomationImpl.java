package org.osmanacademy.webbrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebBrowserAutomationImpl implements WebBrowserAutomation {
    private WebDriver driver;
    private final WebBrowserType webBrowserType;

    public WebBrowserAutomationImpl(WebBrowserType webBrowserType) {
        this.webBrowserType = webBrowserType;
    }

    @Override
    public void openBrowser() {
        switch (this.webBrowserType) {
            case CHROME:
                driver = new ChromeDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
        }
    }

    @Override
    public void minimize() {
        this.driver.manage().window().minimize();
    }

    @Override
    public void maximize() {
        this.driver.manage().window().maximize();
    }

    @Override
    public void refresh() {
        this.driver.navigate().refresh();
    }

    @Override
    public void openNewTab() {

    }

    @Override
    public void closeTab(CloseAction action) {

    }

    @Override
    public void goBack() {
        this.driver.navigate().back();
    }

    @Override
    public void goForward() {
        this.driver.navigate().forward();
    }

    @Override
    public void click(WebElement webElement) {

    }

    @Override
    public void type(WebElement webElement) {

    }

    @Override
    public void selectDropDown(WebElement webElement) {

    }

    @Override
    public void getText(WebElement webElement) {

    }

    @Override
    public void closeBrowser() {

        this.driver.quit();
    }

    @Override
    public void openUrl(String url) {

    }
}
