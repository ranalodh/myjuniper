package com.ibm.testautomation.listener;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.ibm.testautomation.util.PropertiesFileReader;

public class ExtentReportListener {

	public static ExtentHtmlReporter report = null;
	public static ExtentReports extent = null;
	public static ExtentTest test = null;
	public static PropertiesFileReader obj = new PropertiesFileReader();
	private final static Logger LOGGER = Logger.getLogger(ExtentReportListener.class.getName());

	/**
	 * 
	 * @return
	 */
	public static ExtentReports setUp() {
		String reportLocation = System.getProperty("screenshotDirectory", "Reports") + File.separator
				+ "_MyJuniper.html";
		report = new ExtentHtmlReporter(reportLocation);
		report.config().setDocumentTitle("Application Flow Check Test Report");
		report.config().setReportName("Application Flow Check Test Report");
		report.config().setTheme(Theme.STANDARD);
		LOGGER.info("Extent Report location initialized . . .");
		report.start();

		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Application", "Application Flow Check Test Report");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		LOGGER.info("System Info. set in Extent Report");
		return extent;
	}

	/**
	 * 
	 * @param teststatus
	 * @param driver
	 * @param extenttest
	 * @param throwable
	 * @param appPort
	 */
	public static void testStepHandle(String teststatus, WebDriver driver, ExtentTest extenttest, String throwable) {
		switch (teststatus) {
		case "FAIL":
			extenttest.fail(MarkupHelper.createLabel("Test Case is Failed : ", ExtentColor.RED));
			// extenttest.error(throwable.fillInStackTrace());
			extenttest.error(throwable);
			LOGGER.info("FAILED MESSAGE : " + throwable);
			try {
				extenttest.addScreenCaptureFromPath(captureScreenShot(driver));
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (driver != null) {
				driver.quit();
			}
			break;

		case "PASS":
			extenttest.pass(MarkupHelper.createLabel("Test Case is Passed : ", ExtentColor.GREEN));
			break;

		default:
			break;
		}
	}

	/**
	 * 
	 * @param driver
	 * @param appPort
	 * @return
	 * @throws IOException
	 */
	public static String captureScreenShot(WebDriver driver) throws IOException {

		String screenshotDirectory = System.getProperty("screenshotDirectory", "target/screenshots");
		String screenshotAbsolutePath = screenshotDirectory + File.separator + System.currentTimeMillis() + "_"
				+ "MyJuniper" + ".jpg";
		TakesScreenshot screen = (TakesScreenshot) driver;
		File src = screen.getScreenshotAs(OutputType.FILE);
		File target = new File(screenshotAbsolutePath);
		FileUtils.copyFile(src, target);
		return screenshotAbsolutePath;
	}
}