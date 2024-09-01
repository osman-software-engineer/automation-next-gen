package org.osmanacademy.interfaces;

/**
 * The WebBrowser interface represents a web browser and provides methods to interact with it.
 */
public interface WebBrowser {

    void openBrowser() throws Exception;

    void navigateToURL(String url) throws Exception;

    void closeBrowser() throws Exception;;
}
