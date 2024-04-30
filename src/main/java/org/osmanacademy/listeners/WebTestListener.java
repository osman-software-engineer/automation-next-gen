package org.osmanacademy.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

public class WebTestListener implements ITestListener {

    private static final Logger logger = LoggerFactory.getLogger(WebTestListener.class);
    /**
     * Invoked each time before a test will be invoked. The <code>ITestResult</code> is only partially
     * filled with the references to class, method, start millis and status.
     *
     * @param result the partially filled <code>ITestResult</code>
     * @see ITestResult#STARTED
     */
    @Override
    public void onTestStart(ITestResult result) {
        String testName = getFormattedTestName(result);
        logger.info("#############################################################");
        logger.info(STR."# \{testName} Status: Started");
        logger.info("#############################################################");
    }

    /**
     * Invoked each time a test succeeds.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SUCCESS
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = getFormattedTestName(result);
        logger.info("#############################################################");
        logger.info(STR."\{testName} Status: Passed");
        logger.info("#############################################################");
    }

    private static String getFormattedTestName(ITestResult result) {
        ITestContext context = result.getTestContext();
        String testName = result.getMethod().getMethodName();
        testName = Character.toUpperCase(testName.charAt(0)) + testName.substring(1);
        return testName;
    }

    /**
     * Invoked each time a test fails.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#FAILURE
     */
    @Override
    public void onTestFailure(ITestResult result) {
        String testName = getFormattedTestName(result);
        logger.info("#############################################################");
        logger.info(STR."\{testName} Status: Failed");
        logger.info("#############################################################");
        Exception e = (Exception) result.getTestContext().getAttribute("Exception");
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();
        logger.error(exceptionAsString);


    }

    /**
     * Invoked each time a test is skipped.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SKIP
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    /**
     * Invoked each time a method fails but has been annotated with successPercentage and this failure
     * still keeps it within the success percentage requested.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SUCCESS_PERCENTAGE_FAILURE
     */
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    /**
     * Invoked each time a test fails due to a timeout.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     */
    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    /**
     * Invoked before running all the test methods belonging to the classes inside the &lt;test&gt;
     * tag and calling all their Configuration methods.
     *
     * @param context The test context
     */
    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    /**
     * Invoked after all the test methods belonging to the classes inside the &lt;test&gt; tag have
     * run and all their Configuration methods have been called.
     *
     * @param context The test context
     */
    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }
}
