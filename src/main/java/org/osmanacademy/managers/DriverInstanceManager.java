package org.osmanacademy.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.osmanacademy.exceptions.AutomationNextGenException;
import org.osmanacademy.listeners.WebBrowserListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The DriverManager class is responsible for managing the web drivers used in the automation framework.
 */
public class DriverInstanceManager {
    private Logger logger = LoggerFactory.getLogger(DriverInstanceManager.class);

    private WebDriver driver;

    private WebDriver original;

    private EventFiringDecorator eventFiringWebDriver;

    private WebDriverListener listener;

    private WebDriver decorated;

    /**
     * Opens a ChromeDriver instance with options provided by the ChromeOptionsManager.
     * If the 'start.maximized' property is set to true in the webBrowserProperties, the ChromeDriver will start maximized.
     * If the 'mobile.emulation' property is set to true in the webBrowserProperties, the ChromeDriver will emulate a mobile device.
     * The 'mobile.device.name' property should contain the name of the desired mobile device to be emulated.
     *
     * @throws AutomationNextGenException if there was an error opening the ChromeDriver or if the device name for mobile emulation is invalid.
     */
    public void openChromeDriver(String propertyFileName) throws AutomationNextGenException {
        ChromeOptionsManager chromeOptionsManager = new ChromeOptionsManager(propertyFileName);
        chromeOptionsManager.manageStartMaximized();
        chromeOptionsManager.manageMobileEmulation();
        //setDriver(new ChromeDriver(chromeOptionsManager.getChromeOptions()));
        original = new ChromeDriver(chromeOptionsManager.getChromeOptions());
        listener = new WebBrowserListener();
        decorated = new EventFiringDecorator(listener).decorate(original);

    }

    /**
     * Opens a new instance of Firefox browser.
     *
     * @param propertyFileName the name of the property file containing the web browser configuration.
     * @throws AutomationNextGenException if there was an error opening the Firefox browser.
     */
    public void openFirefoxDriver(String propertyFileName) throws AutomationNextGenException {
        setDriver(new FirefoxDriver());
    }

    /**
     * Opens a new instance of Edge browser.
     *
     * @param propertyFileName the name of the property file containing the web browser configuration.
     * @throws AutomationNextGenException if there was an error opening the Edge browser.
     */
    public void openEdgeBrowser(String propertyFileName) throws AutomationNextGenException {
        setDriver(new EdgeDriver());
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

    public WebDriver getOriginal() {
        return original;
    }

    public void setOriginal(WebDriver original) {
        this.original = original;
    }

    public EventFiringDecorator getEventFiringWebDriver() {
        return eventFiringWebDriver;
    }

    public void setEventFiringWebDriver(EventFiringDecorator eventFiringWebDriver) {
        this.eventFiringWebDriver = eventFiringWebDriver;
    }

    public WebDriverListener getListener() {
        return listener;
    }

    public void setListener(WebDriverListener listener) {
        this.listener = listener;
    }

    public WebDriver getDecorated() {
        return decorated;
    }

    public void setDecorated(WebDriver decorated) {
        this.decorated = decorated;
    }
}
