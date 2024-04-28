package org.osmanacademy.listeners;

import net.lingala.zip4j.ZipFile;
import org.apache.commons.io.FileUtils;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.annotations.Listeners;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Listeners()
public class WebSuiteListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {

        // FileUtils.cleanDirectory(new File(System.getProperty("user.dir") +
        // "\\allure-results"));
        // SendEmail.onStart(suite.getName());

    }

    @Override
    public void onFinish(ISuite suite) {

        // String reportName = prepareZippedFormatReport(suite);

        // new
        // ExecuteCommandsInWindowsCmd().openAllureReport(System.getProperty("user.dir")
        // + "\\allure-results");

        // SendEmail.onFinishWithOutAttachment(suite);

    }

    /**
     * @param suite
     * @throws IOException
     */
    public String prepareZippedFormatReport(ISuite suite) throws IOException {
/*
        try {
            // 1) Copy Bat File
            String srcFile = WebTestFileLocations.OPEN_REPORT_BATCH_FILE;
            String destFile = WebTestFileLocations.ALLURE_RESULTS;
            FileUtils.copyFileToDirectory(new File(WebTestFileLocations.OPEN_REPORT_BATCH_FILE),
                    new File(WebTestFileLocations.ALLURE_RESULTS));
            // 2) Zip File
            ZipFile zipFile = ZipUnZipFilesUtil.zipFile(WebTestFileLocations.ALLURE_RESULTS,
                    WebTestFileLocations.ALLURE_RESULTS_ZIPPED);
            // 3) Rename File
            Instant nowUtc = Instant.now();
            ZoneId washingtonTimeZone = ZoneId.of("America/Los_Angeles");
            ZonedDateTime currentTimeWashington = ZonedDateTime.ofInstant(nowUtc, washingtonTimeZone);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatDateTime = currentTimeWashington.format(formatter).replace(":", "_").replace(" ", "_")
                    .replace("-", "_");
            String reportName = suite.getName().trim();
            if (reportName != null && reportName.split(":::")[2].trim().equalsIgnoreCase("Regression")) {
                reportName = WebTestFileLocations.ALLURE_RESULTS_TO_BE_SENT + "REG_AUT_TEST_RESULTS_" + formatDateTime
                        + ".zip";
            }
            FileUtils.copyFile(new File(WebTestFileLocations.ALLURE_RESULTS_ZIPPED), new File(reportName));
            return reportName;
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException();
        }
        */
        return null;
    }
}