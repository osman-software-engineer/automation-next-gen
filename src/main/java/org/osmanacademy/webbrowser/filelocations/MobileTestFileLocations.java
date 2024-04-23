package org.osmanacademy.webbrowser.filelocations;

import java.io.File;

public class MobileTestFileLocations {
	public static final String TEST_DATA_LOCATION_SMOKE = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "test" + File.separator + "resources" + File.separator + "testdata" + File.separator
			+ "smoke";

	public static final String TEST_EXECUTION_PROPERTY_FILE = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "main" + File.separator + "resources" + File.separator + "mobile_testexecution.properties";

	public static final String ROLES_PROPERTY_FILES = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "profiles" + File.separator + "resources" + File.separator
			+ "webtestexecution.properties";

	public static final String PROFILE = System.getProperty("user.dir") + File.separator +  "src"
			+ File.separator + "main" + File.separator + "profiles";
	public static final String TEST_RUNNER_DAY_ZERO = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "test" + File.separator + "resources" + File.separator + "testrunner" + File.separator
			+ "smoke";
	public static final String OPEN_REPORT_BATCH_FILE = System.getProperty("user.dir").replace("HBE-TestAutomationSuite-Web", "HBE-TestAutomationSuite-Utils") + File.separator + "src"
			+ File.separator + "main" + File.separator + "java" + File.separator + "org"+ File.separator + "wahpf"+ File.separator + "automationutils"+ File.separator + "reports"+ File.separator + "OpenReport.bat";

	public static final String ALLURE_RESULTS = System.getProperty("user.dir")+ File.separator + "allure-results";

	public static final String ALLURE_RESULTS_ZIPPED = System.getProperty("user.dir")+ File.separator + "allure-results.zip";

	public static final String ALLURE_RESULTS_TO_BE_SENT = System.getProperty("user.dir")+ File.separator;
	private MobileTestFileLocations() {

	}
}
