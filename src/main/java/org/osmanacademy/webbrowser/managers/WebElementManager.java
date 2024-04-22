package org.osmanacademy.webbrowser.managers;


import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.osmanacademy.webbrowser.exceptions.WebBrowserAutomationException;


public class WebElementManager{

    private WebDriver driver;

    private WebElement webElement;

    private List<WebElement> webElements;

    private JavascriptExecutor javaScriptExecutor;

    public WebElementManager(WebDriver driver) {
        setDriver(driver);
        setJavaScriptExecutor((JavascriptExecutor) getDriver());
    }


    public WebElement getWebElementFromDOM(By locator) throws WebBrowserAutomationException {
        try {
            if (locator == null) {
                throw new WebBrowserAutomationException("Element From UI is Null");
            } else {
                setElement(getDriver().findElement(locator));
                return getWebElementFromMemory();
            }
        } catch (Exception e) {
            throw new WebBrowserAutomationException(e.getMessage());
        }

    }


    public List<WebElement> getWebElements(By elementFromUi) throws WebBrowserAutomationException {
        try {
            if (elementFromUi == null) {
                throw new WebBrowserAutomationException("Element From UI is Null");
            } else {
                setWebElements(getDriver().findElements(elementFromUi));
                return getWebElements();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new WebBrowserAutomationException(e.getMessage());
        }

    }


    public Boolean isElementDisplayed(By elementFromUi) throws WebBrowserAutomationException {
        try {
            if (ObjectUtils.isEmpty(elementFromUi)) {
                throw new WebBrowserAutomationException("Element From UI is Null");
            }
            return getWebElementFromDOM(elementFromUi).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    public Boolean isElementDisplayed(WebElement elementFromUi) throws WebBrowserAutomationException {
        try {
            if (ObjectUtils.isEmpty(elementFromUi)) {
                throw new WebBrowserAutomationException("Element From UI is Null");
            }
            return elementFromUi.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    public Boolean isElementEnabled(By elementFromUi) throws WebBrowserAutomationException {

        try {
            if (ObjectUtils.isEmpty(elementFromUi)) {
                throw new WebBrowserAutomationException("Element From UI is Null");
            }
            return getWebElementFromDOM(elementFromUi).isEnabled();
        } catch (Exception e) {
            throw new WebBrowserAutomationException(e.getMessage());
        }
    }


    public Boolean isElementSelected(By elementFromUi) throws WebBrowserAutomationException {

        try {
            if (ObjectUtils.isEmpty(elementFromUi)) {
                throw new WebBrowserAutomationException("Element From UI is Null");
            }
            return getWebElementFromDOM(elementFromUi).isSelected();
        } catch (Exception e) {
            throw new WebBrowserAutomationException(e.getMessage());
        }
    }


    public String getText(By elementFromUi) throws WebBrowserAutomationException {
        try {
            return getWebElementFromDOM(elementFromUi).getText().trim();
        } catch (Exception e) {
            throw new WebBrowserAutomationException(e.getMessage());
        }
    }


    public Dimension getSize(By elementFromUi) throws WebBrowserAutomationException {

        try {
            return getWebElementFromDOM(elementFromUi).getSize();
        } catch (Exception e) {
            throw new WebBrowserAutomationException(e.getMessage());
        }
    }


    public Point getLocation(By elementFromUi) throws WebBrowserAutomationException {

        try {
            return getWebElementFromDOM(elementFromUi).getLocation();
        } catch (Exception e) {
            throw new WebBrowserAutomationException(e.getMessage());
        }
    }


    public String getCssValue(By elementFromUi, String propertyName) throws WebBrowserAutomationException {

        try {
            return getWebElementFromDOM(elementFromUi).getCssValue(propertyName);
        } catch (Exception e) {
            throw new WebBrowserAutomationException(e.getMessage());
        }
    }


    public String getAttribute(By elementFromUi, String name) throws WebBrowserAutomationException {
        try {
            return getWebElementFromDOM(elementFromUi).getAttribute(name);
        } catch (Exception e) {
            throw new WebBrowserAutomationException(e.getMessage());
        }
    }

    public String getAttribute(WebElement elementFromUi, String name) throws WebBrowserAutomationException {
        try {
            return elementFromUi.getAttribute(name);
        } catch (Exception e) {
            throw new WebBrowserAutomationException(e.getMessage());
        }
    }

    public String getTagName(By elementFromUi) throws WebBrowserAutomationException {
        try {
            return getWebElementFromDOM(elementFromUi).getTagName().trim();
        } catch (Exception e) {
            throw new WebBrowserAutomationException(e.getMessage());
        }
    }


    public void scrollElementIntoView(By elementFromUi) throws WebBrowserAutomationException {
        try {
            getJavaScriptExecutor().executeScript("arguments[0].scrollIntoView(true);", getWebElementFromDOM(elementFromUi));
        } catch (Exception e) {
            throw new WebBrowserAutomationException(e.getMessage());
        }
    }

    public void scrollElementIntoView(WebElement elementFromUi) throws WebBrowserAutomationException {
        try {
            getJavaScriptExecutor().executeScript("arguments[0].scrollIntoView(true);", elementFromUi);
        } catch (Exception e) {
            throw new WebBrowserAutomationException(e.getMessage());
        }
    }

    public Object getDataFromListOfAvailableAttributes(By elementFromUi) throws WebBrowserAutomationException {
        try {
            setElement(getWebElementFromDOM(elementFromUi));
            return getJavaScriptExecutor().executeScript(
                    "var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;",
                    getWebElementFromMemory());
        } catch (Exception e) {
            throw new WebBrowserAutomationException(e.getMessage());
        }
    }

    public Object getDataFromListOfAvailableAttributes(WebElement elementFromUi) throws WebBrowserAutomationException {
        try {
            setElement(elementFromUi);
            return getJavaScriptExecutor().executeScript(
                    "var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;",
                    getWebElementFromMemory());
        } catch (Exception e) {
            throw new WebBrowserAutomationException(e.getMessage());
        }
    }
    public WebDriver getDriver() {
        return driver;
    }

    private void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getWebElementFromMemory() {
        return webElement;
    }

    private void setElement(WebElement webElement) {
        this.webElement = webElement;
    }

    /**
     * @return the webElements
     */
    public List<WebElement> getWebElements() {
        return webElements;
    }

    /**
     * @param webElements the webElements to set
     */
    public void setWebElements(List<WebElement> webElements) {
        this.webElements = webElements;
    }

    /**
     * @return the javaScriptExecutor
     */
    public JavascriptExecutor getJavaScriptExecutor() {
        return javaScriptExecutor;
    }

    /**
     * @param javaScriptExecutor the javaScriptExecutor to set
     */
    public void setJavaScriptExecutor(JavascriptExecutor javaScriptExecutor) {
        this.javaScriptExecutor = javaScriptExecutor;
    }
}
