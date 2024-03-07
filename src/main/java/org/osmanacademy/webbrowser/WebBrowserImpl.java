package org.osmanacademy.webbrowser;

import com.epam.healenium.SelfHealingDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.osmanacademy.common.PropertiesFileLoader;

import java.time.Duration;

public class WebBrowserImpl implements WebBrowser {
    public static final Duration WAIT_TIMEOUT = Duration.ofSeconds(10);
    private WebDriver driver;

    private final PropertiesFileLoader webProperProperties;

    public WebBrowserImpl() {
        webProperProperties = new PropertiesFileLoader("web-browser.properties");
    }

    @Override
    public SelfHealingDriver openBrowser() throws Exception {
        try {
            String name = webProperProperties.getProperty("web.browser.name");
            switch (name) {
                case "edge":
                    this.driver = new EdgeDriver();
                    break;
                case "firefox":
                    this.driver = new FirefoxDriver();
                    break;
                default:
                  openChromeDriver();

            }
            return SelfHealingDriver.create(this.driver);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void openChromeDriver() throws Exception {
        try {
            ChromeOptions options = new ChromeOptions();
            if (webProperProperties.getProperty("start.maximized").equals("true")) {
                options.addArguments("--start-maximized");
            }
            WebDriverManager.chromedriver().setup();
            this.driver = new ChromeDriver(options);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
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
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.open()");
    }

    @Override
    public boolean switchToTabWithTitle(String title) throws Exception {
        // Store the original window handle for reference
        String originalWindow = this.driver.getWindowHandle();
        boolean found = false;
        try {
            // Iterate over all open tabs
            for (String handle : this.driver.getWindowHandles()) {
                // Switch to each tab
                this.driver.switchTo().window(handle);

                // Check if the tab's title matches the desired title
                if (this.driver.getTitle().equals(title)) {
                    found = true;
                    break;
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        // If the tab was not found, switch back to the original window
        if (!found) {
            this.driver.switchTo().window(originalWindow);
        }
        return found;
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
    public void click(By locator) throws Exception {
        waitForElementToBeClickable(locator).click();
    }

    @Override
    public void type(By locator) {

    }

    @Override
    public void selectDropDown(By locator) {

    }

    @Override
    public String getText(By locator) {

        return null;
    }

    @Override
    public void closeBrowser() {
        this.driver.quit();
    }

    private WebElement waitForElementToBeClickable(By locator) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, WAIT_TIMEOUT);
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void openUrl(String url) throws Exception {
        try {
            this.driver.navigate().to(url);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}