package org.osmanacademy.listeners;

import io.qameta.allure.Attachment;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.osmanacademy.webbrowser.exceptions.WebBrowserAutomationException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class WebTestListener implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestContext context = result.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("WebDriver");
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        Long windowHeight = (Long) jsExec.executeScript("return window.innerHeight;");
        Long webpageHeight = (Long) jsExec.executeScript("return document.body.scrollHeight;");
        Long currentWindowScroll = 0L;
        while (currentWindowScroll <= webpageHeight) {
            jsExec.executeScript("window.scrollTo(0, " + currentWindowScroll + ");");
            try {
                takeScreenShotOnSuccess(driver);
            } catch (WebBrowserAutomationException e) {
                throw new RuntimeException(e);
            }
            currentWindowScroll = currentWindowScroll + windowHeight;

        }

    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestContext context = result.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("WebDriver");
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        Long windowHeight = (Long) jsExec.executeScript("return window.innerHeight;");
        Long webpageHeight = (Long) jsExec.executeScript("return document.body.scrollHeight;");
        Long currentWindowScroll = 0L;
        while (currentWindowScroll <= webpageHeight) {
            jsExec.executeScript("window.scrollTo(0, " + currentWindowScroll + ");");
            try {
                takeScreenShotOnFailure(driver);
            } catch (WebBrowserAutomationException e) {
                throw new RuntimeException(e);
            }
            currentWindowScroll = currentWindowScroll + windowHeight;

        }
    }

    @Attachment(value = "Web Page ScreenShot on Test Success", type = "image/png")
    public byte[] takeScreenShotOnSuccess(WebDriver driver) throws WebBrowserAutomationException {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

    }

    @Attachment(value = "Web Page ScreenShot on Test Failure", type = "image/png")
    public byte[] takeScreenShotOnFailure(WebDriver driver) throws WebBrowserAutomationException {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

    }
}
	





