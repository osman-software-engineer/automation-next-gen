package org.osmanacademy.managers;

import com.microsoft.playwright.*;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;
import java.util.Properties;

public class PlaywrightInstanceManager {
    private final Logger logger = LoggerFactory.getLogger(PlaywrightInstanceManager.class);
    private  Playwright playwright;
    private  Browser browser;
    private  BrowserContext browserContext;
    private  BrowserType browserType;
    private Page page;
    private final ChromiumLaunchOptionsManager chromiumLaunchOptionsManager;

    public PlaywrightInstanceManager(Properties properties) {
        this.chromiumLaunchOptionsManager = new ChromiumLaunchOptionsManager(properties);
    }

    public void openChromeWebBrowser() {
        this.chromiumLaunchOptionsManager.manageHeadless();
        this.chromiumLaunchOptionsManager.manageStartMaximized();
        this.playwright = Playwright.create();
        this.browser = this.playwright.chromium().launch(this.chromiumLaunchOptionsManager.getChromiumLaunchOptions());
        this.browserContext = this.browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        this.page = this.browserContext.newPage();
    }

    public Playwright getPlaywright() {
        ObjectUtils.requireNonEmpty(this.playwright,"Playwright Object Cannot be Null");
        return playwright;
    }

    public Page getPage() {
        ObjectUtils.requireNonEmpty(this.playwright,"Playwright Object Cannot be Null");
        return page;
    }
}
