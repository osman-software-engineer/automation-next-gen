package org.osmanacademy.webbrowser;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.openqa.selenium.*;
import org.osmanacademy.common.PropertiesFileLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrowserLauncher {

    /**
     * Launches a specified browser and returns the Browser instance.
     * The method configures the browser to launch in non-headless mode by default,
     * which can be adjusted as needed.
     *
     * @param browserType The type of browser to launch.
     * @return The launched Browser instance.
     */
    public static Browser launchBrowser(String browserType) {
        Playwright playwright = Playwright.create();
        Browser browser;

        switch (browserType) {
            case "chrome":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "edge":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("msedge"));
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }

        return browser;
    }

    /**
     * Example main method to demonstrate usage.
     */
    public static void main(String[] args) {
        // Example usage: Launch Chrome
        Browser chrome = launchBrowser("chrome");
        Page page = chrome.newPage();
        page.navigate("https://example.com");
        System.out.println("Opened in Chrome: " + page.title());

        // Example usage: Launch Firefox
        Browser firefox = launchBrowser("firefox");
        page = firefox.newPage();
        page.navigate("https://example.com");
        System.out.println("Opened in Firefox: " + page.title());

        // Close browsers
        chrome.close();
        firefox.close();
    }

}
