package com.ibm.testautomation.listener;

import java.util.logging.Logger;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ITestListenerImpl extends ExtentReportListener implements ITestListener
{
	private final static Logger LOGGER = Logger.getLogger(ITestListenerImpl.class.getName());
	
	public void onTestStart(ITestResult result) {
	
	}

	public void onTestSuccess(ITestResult result) {
		
		LOGGER.info("PASS");
		
	}

	public void onTestFailure(ITestResult result) {
		LOGGER.info("FAIL");
	}

	public void onTestSkipped(ITestResult result) {
		LOGGER.info("SKIP");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onStart(ITestContext context) {
		LOGGER.info("Execution started on " + System.getProperty("env") + " env...");
		extent= setUp();
	}

	public void onFinish(ITestContext context) {
		LOGGER.info("Execution completed on " + System.getProperty("env") + " env...");
		extent.flush();		
		LOGGER.info("Generated Report. . .");	
		
	}
}
