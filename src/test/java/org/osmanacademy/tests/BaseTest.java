package org.osmanacademy.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * The BaseTestSmoke class represents the base test class for smoke tests. It provides methods for setting up and tearing down test environments, as well as accessing the web browser
 * and website URL.
 */
public class BaseTest {
    public static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeSuite
    public void beforeSuite() {
        // Place your code here
    }
    @AfterSuite
    public void afterSuite() {
        // Place your code here
    }

}
