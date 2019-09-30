package com.ibm.testautomation.util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CommonUtil {
	
	public static WebDriver webDriver = null; 
	public final static Logger LOGGER = Logger.getLogger(CommonUtil.class.getName());
	public final static int ELEMENT_WAIT_TIMEOUT = 2000;
	public final static String TAB_SERVICE_REQUEST = "SR";
	public final static String TAB_PRODUCT = "PRODUCTS";
	public final static String TAB_CONTRACTS = "CONTRACTS";
	public final static String TAB_RENEWAL_QUOTES = "RENEWALQUOTES";
	public final static String TAB_RMA = "RMA";
	public final static String TAB_OVERVIEW = "OVERVIEW";


	
	/**
	 * 
	 * @param driver
	 * @param appPort
	 * @throws IOException
	 */
  public void captureScreen(WebDriver driver) throws IOException {
		
		String screenshotDirectory = System.getProperty("screenshotDirectory", "target/screenshots");
        String screenshotAbsolutePath = screenshotDirectory + File.separator + System.currentTimeMillis() + "_"
				+ "MyJuniper" +  ".jpg";
        TakesScreenshot screen = (TakesScreenshot) driver;		
		File src = screen.getScreenshotAs(OutputType.FILE);
		File target = new File(screenshotAbsolutePath);
		FileUtils.copyFile(src, target);
		
	}
  /**
   * 
   * @return
   */
  public static WebDriver getDriver() {

		try {
				System.setProperty("webdriver.chrome.driver","src/main/java/chromedriver_linux64/chromedriver");
			    //System.setProperty("webdriver.chrome.driver","C:\\Selenium\\chromedriver_win32\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.setBinary("/bin/google-chrome");
				options.setHeadless(true);
				webDriver = new ChromeDriver(options);	
				webDriver.manage().window().maximize();		
				webDriver.navigate().refresh();
		}catch (Exception exception) {
			exception.printStackTrace();
		}
		return webDriver;
		}	
  /**
   * 
   * @param element
   * @param driver
   */
  public void hightlightElement(By element, WebDriver driver) {
	  WebElement element_node = driver.findElement(element);
	  JavascriptExecutor jse = (JavascriptExecutor) driver;
	  jse.executeScript("arguments[0].style.border='2px solid red'", element_node);
  }
 
}
