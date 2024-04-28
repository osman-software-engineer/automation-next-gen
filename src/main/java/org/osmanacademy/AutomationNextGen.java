package org.osmanacademy;

import org.osmanacademy.interfaces.*;

public interface AutomationNextGen {
    WebBrowser getWebBrowser();

    TestDataGenerator getTestDataGenerator();

    Database getDatabase();

    Credentials getCredentials();

    TestExecutionEnvironment getEnvironment();

}
