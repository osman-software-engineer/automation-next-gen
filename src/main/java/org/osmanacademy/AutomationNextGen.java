package org.osmanacademy;

import org.osmanacademy.interfaces.*;

public interface AutomationNextGen {
    TestEnvironment getTestEnvironment();

    TestDataGenerator getTestDataGenerator();

    WebBrowser getWebBrowser();

    Database getDatabase();

    UserCredentials getCredentials();


}
