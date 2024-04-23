package org.osmanacademy.listeners;

import com.testautomationsuite.web.exceptionhandling.WebTestException;
import io.qameta.allure.Attachment;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
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
		try {
			ITestContext context = result.getTestContext();
			WebDriver driver = (WebDriver) context.getAttribute("WebDriver");
			JavascriptExecutor jsExec = (JavascriptExecutor) driver;
			Long windowHeight = (Long) jsExec.executeScript("return window.innerHeight;");
			Long webpageHeight = (Long) jsExec.executeScript("return document.body.scrollHeight;");
			Long currentWindowScroll = 0L;
			while (currentWindowScroll <= webpageHeight) {
				jsExec.executeScript("window.scrollTo(0, " + currentWindowScroll + ");");
				takeScreenShotOnSuccess(driver);
				currentWindowScroll = currentWindowScroll + windowHeight;

			}
		} catch (WebTestException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			ITestContext context = result.getTestContext();
			WebDriver driver = (WebDriver) context.getAttribute("WebDriver");
			JavascriptExecutor jsExec = (JavascriptExecutor) driver;
			Long windowHeight = (Long) jsExec.executeScript("return window.innerHeight;");
			Long webpageHeight = (Long) jsExec.executeScript("return document.body.scrollHeight;");
			Long currentWindowScroll = 0L;
			while (currentWindowScroll <= webpageHeight) {
				jsExec.executeScript("window.scrollTo(0, " + currentWindowScroll + ");");
				takeScreenShotOnFailure(driver);
				currentWindowScroll = currentWindowScroll + windowHeight;

			}
		} catch (WebTestException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {

	}

	@Attachment(value = "Web Page ScreenShot on Test Success", type = "image/png")
	public byte[] takeScreenShotOnSuccess(WebDriver driver) throws WebTestException {
		try {
			return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebTestException(e.getMessage());
		}

	}

	@Attachment(value = "Web Page ScreenShot on Test Failure", type = "image/png")
	public byte[] takeScreenShotOnFailure(WebDriver driver) throws WebTestException {
		try {
			return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebTestException(e.getMessage());
		}

	}

}
