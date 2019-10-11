package com.ibm.testautomation.listener;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.ibm.testautomation.util.PropertiesFileReader;

public class ITestListenerImpl extends ExtentReportListener implements ITestListener
{
	private final static Logger LOGGER = Logger.getLogger(ITestListenerImpl.class.getName());
	PropertiesFileReader obj = new PropertiesFileReader();
	
	/**
	 * 
	 */
	public void onTestStart(ITestResult result) {
	
	}
	/**
	 * 
	 */
	public void onTestSuccess(ITestResult result) {
		
		LOGGER.info("PASS");
		
	}
	/**
	 * 
	 */
	public void onTestFailure(ITestResult result) {
		LOGGER.info("FAIL");
	}
	/**
	 * 
	 */
	public void onTestSkipped(ITestResult result) {
		LOGGER.info("SKIP");
	}
	/**
	 * 
	 */
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}
	/**
	 * 
	 */
	public void onStart(ITestContext context) {
		
		Properties properties;
		try {
			properties = obj.getProperty();
			LOGGER.info("Execution started on " + properties.getProperty("env"));
			extent= setUp();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	/**
	 * 
	 */
	public void onFinish(ITestContext context) {
		
		Properties properties;
		try {
			properties = obj.getProperty();
			LOGGER.info("Execution completed on " + properties.getProperty("env"));
			extent.flush();		
			LOGGER.info("Generated Report. . .");	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
