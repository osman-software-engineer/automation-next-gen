package org.osmanacademy.webbrowser.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.osmanacademy.webbrowser.exceptions.WebBrowserAutomationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The DriverManager class is responsible for managing the web drivers used in the automation framework.
 */
public class DriverInstanceManager {
    private Logger logger = LoggerFactory.getLogger(DriverInstanceManager.class);

    private WebDriver driver;

    /**
     * Opens a ChromeDriver instance with options provided by the ChromeOptionsManager.
     * If the 'start.maximized' property is set to true in the webBrowserProperties, the ChromeDriver will start maximized.
     * If the 'mobile.emulation' property is set to true in the webBrowserProperties, the ChromeDriver will emulate a mobile device.
     * The 'mobile.device.name' property should contain the name of the desired mobile device to be emulated.
     *
     * @throws WebBrowserAutomationException if there was an error opening the ChromeDriver or if the device name for mobile emulation is invalid.
     */
    public WebDriver openChromeDriver(String propertyFileName) throws WebBrowserAutomationException {
        ChromeOptionsManager chromeOptionsManager = new ChromeOptionsManager(propertyFileName);
        chromeOptionsManager.manageStartMaximized();
        chromeOptionsManager.manageMobileEmulation();
        setDriver(new ChromeDriver(chromeOptionsManager.getChromeOptions()));
        return getDriver();
    }

    /**
     * Opens a new instance of Firefox browser.
     *
     * @param propertyFileName the name of the property file containing the web browser configuration.
     * @throws WebBrowserAutomationException if there was an error opening the Firefox browser.
     */
    public WebDriver openFirefoxDriver(String propertyFileName) throws WebBrowserAutomationException {
        setDriver(new FirefoxDriver());
        return getDriver();
    }

    /**
     * Opens a new instance of Edge browser.
     *
     * @param propertyFileName the name of the property file containing the web browser configuration.
     * @throws WebBrowserAutomationException if there was an error opening the Edge browser.
     */
    public WebDriver openEdgeBrowser(String propertyFileName) throws WebBrowserAutomationException {
        setDriver(new EdgeDriver());
        return getDriver();
    }

    /**
     * Returns the current WebDriver instance.
     *
     * @return the current WebDriver instance.
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Sets the WebDriver instance to be used for browser automation.
     *
     * @param driver the WebDriver instance to be set
     */
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}
