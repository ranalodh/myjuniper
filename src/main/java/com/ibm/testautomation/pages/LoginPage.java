package com.ibm.testautomation.pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ibm.testautomation.util.ActionLib;
import com.ibm.testautomation.util.CommonUtil;
import com.ibm.testautomation.util.PropertiesFileReader;

public class LoginPage extends BasePage {

	private By loginFormContainer = By.id("loginFormContainer");
	private By userId = By.id("userid");
	private By password = By.id("password");
	private By loginButton = By.xpath("//div[4]//input[1]");
	private By rememberMe = By.xpath("rememberme");
	private By errorDiv = By.xpath("//div[@class='error-message']");
	ActionLib actionLib = new ActionLib();
	CommonUtil commonUtil = new CommonUtil();
	PropertiesFileReader obj = new PropertiesFileReader();

	/**
	 * 
	 * @param driver
	 * @return
	 * @throws Exception
	 */
	public String getTitle(WebDriver driver) throws Exception {
		return driver.getTitle();
	}

	// login MyJuniper Application
	/**
	 * 
	 * @param driver
	 * @throws Throwable
	 */
	public void login(WebDriver driver) throws Throwable {
		
		Properties properties = obj.getProperty();

		if (actionLib.isElementExist(loginFormContainer, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT)) {
			actionLib.sendValueToInput(userId, driver, properties.getProperty("username"));
			actionLib.sendValueToInput(password, driver, properties.getProperty("password"));
			// actionLib.javascriptClick(rememberMe, driver);
			commonUtil.captureScreen(driver);
			actionLib.javascriptClick(loginButton, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT);
		} else {

		}

	}

}
