package org.osmanacademy.managers;

import com.microsoft.playwright.BrowserType;
import org.apache.commons.lang3.StringUtils;
import org.osmanacademy.enums.ChromiumOptionsArgs;
import org.osmanacademy.enums.WebBrowserProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * The ChromiumLaunchOptionsManager class manages the launch options for Chromium browser.
 */
public class ChromiumLaunchOptionsManager {
    private static final String TRUE = "true";
    private static final String FALSE = "false";
    private static final Logger logger = LoggerFactory.getLogger(ChromiumLaunchOptionsManager.class);
    private Properties properties;
    private BrowserType.LaunchOptions chromiumLaunchOptions;
    private List<String> args;

    /**
     * Constructor to initialize ChromiumLaunchOptionsManager with given properties.
     */
    public ChromiumLaunchOptionsManager(Properties properties) {
        this.properties = properties;
        this.chromiumLaunchOptions = new BrowserType.LaunchOptions();
        this.args = new ArrayList<>();
    }

    /**
     * Manages the headless option for Chromium browser launch.
     */
    public void manageHeadless() {
        logger.info("Trying to Check for Chromium Launch Options Flags");
        String propKey = this.properties.getProperty(WebBrowserProperties.HEADLESS.getKeyName());
        if (StringUtils.equals(propKey, TRUE)) {
            logger.info("Headless Flag is Set to True");
            getChromiumLaunchOptions().setHeadless(true);
        } else {
            logger.info("Start Headless is Set to False");
            getChromiumLaunchOptions().setHeadless(false);
        }
    }

    /**
     * Method to manage starting of browser in maximized mode.
     */
    public void manageStartMaximized() {
        logger.info("Trying to Check for Chromium Launch Options");
        String propKey = this.properties.getProperty(WebBrowserProperties.START_MAXIMIZED.getKeyName());
        if (StringUtils.equals(propKey, TRUE)) {
            logger.info("Start Maximized is Set to True");
            addArgToChromiumOptionAndLog(ChromiumOptionsArgs.START_MAXIMIZED);
        } else {
            logger.info("Start Maximized is Set to False");
        }
    }

    private void addArgToChromiumOptionAndLog(ChromiumOptionsArgs optionArgs) {
        try {
            logger.info("Trying to Add {} argument to chromium launch options.", optionArgs.getArgs());
            this.args.add(optionArgs.getArgs());
            this.chromiumLaunchOptions.setArgs(this.args);
            logger.info(String.format("Successfully added %s argument to chromium launch options.", optionArgs.getArgs()));
        } catch (Exception e) {
            throw new RuntimeException("Exception Occurred while Trying to Add " + optionArgs.name() + "argument to chromium launch options: ", e);
        }
    }

    public BrowserType.LaunchOptions getChromiumLaunchOptions() {
        return chromiumLaunchOptions;
    }


}