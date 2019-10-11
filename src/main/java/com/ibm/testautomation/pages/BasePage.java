package com.ibm.testautomation.pages;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;

public class BasePage {

	private final static Logger LOGGER = Logger.getLogger(BasePage.class.getName());

	/**
	 * 
	 * @param url
	 * @param driver
	 * @throws InterruptedException 
	 */
	public void launchBrowser(String url, WebDriver driver) throws InterruptedException {

		LOGGER.info("Opening MyJuniper Application URL : " + url);

		driver.get(url);

	}
}
