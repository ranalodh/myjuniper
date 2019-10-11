package com.ibm.testautomation.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionLib {

	private final static Logger LOGGER = Logger.getLogger(ActionLib.class.getName());
	Select dropdown = null;
	
	/**
	 * 
	 * @param objectref
	 * @param driver
	 * @param intSyncTimeOut
	 * @throws Throwable
	 */
	public void click(By objectref, WebDriver driver, int intSyncTimeOut) throws Throwable {
		try {

			
			LOGGER.info("Into Click() :: Object Reference Is:" + objectref);
			LOGGER.info("Clicking on Webelement: " + objectref);

			LOGGER.info("...................Click Event Started........");

			//driver.findElement(objectref).click();

			WebElement element = WaitForObjectToLoadAndReturnIfExist(objectref, intSyncTimeOut, driver);

			if (element!=null){
				element.click();
				LOGGER.info("...............Click Event Completed........");

			} else {

				LOGGER.info("........Unable to Click - Object Not Found........");	
			}	

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.warning("Not able to Click --- " + e.getMessage());

		}
	}

	/*public void wait(WebDriver driver) throws Throwable

	{

		try {
			LOGGER.info("Wait for 10 seconds");
			
			LOGGER.info("Into wait() :: Driver Is:" + driver);

			LOGGER.info("...................wait Started........");

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			LOGGER.info("...................wait Completed........");

		} catch (Exception e) {
			LOGGER.warning("Not able to Wait --- " + e.getMessage());

		}
	}
*/
	/**
	 * 
	 * @param objectref
	 * @param driver
	 * @param intSyncTimeOut
	 * @return
	 * @throws Throwable
	 */
	public boolean isElementExist(By objectref, WebDriver driver, int intSyncTimeOut) throws Throwable

	{
		boolean isElementExist = false;
		try {

			LOGGER.info("Locating object " + objectref);
			
			LOGGER.info("Into findObject() :: Driver Is:" + driver);
			LOGGER.info("Into findObject() :: Object Reference Is:" + objectref);
			LOGGER.info("...................findObject Event Started........");
			
			WebElement element = WaitForObjectToLoadAndReturnIfExist(objectref, intSyncTimeOut, driver);

			if (element!=null){
				isElementExist = true;
				LOGGER.info("Object found");

			}else {
				isElementExist = false;
				LOGGER.info("Object Not found");
			}
		} catch (Exception e) {
			LOGGER.warning("Not able to Locate object --- " + e.getMessage());

		}
		return isElementExist;

	}
	/**
	 * 
	 * @param objectref
	 * @param driver
	 * @param intSyncTimeOut
	 * @return
	 * @throws Throwable
	 */
	public int getElementSize(By objectref, WebDriver driver, int intSyncTimeOut) throws Throwable

	{
		int elementSize = 0;
		try {

			LOGGER.info("Locating object " + objectref);
			
			LOGGER.info("Into findObject() :: Driver Is:" + driver);
			LOGGER.info("Into findObject() :: Object Reference Is:" + objectref);

			LOGGER.info("...................findObject Event Started........");
			
			WebElement element = WaitForObjectToLoadAndReturnIfExist(objectref, intSyncTimeOut, driver);
			
			if (element!=null){
				LOGGER.info("Object found");
				int E = element.findElements(objectref).size();

				if (E > 0) {
					LOGGER.info("Object found");
					elementSize = E;

				} else {
					elementSize = E;
				}

			}else {
				LOGGER.info("Object Not found");
				elementSize = 0;
			}
		} catch (Exception e) {
			LOGGER.warning("Not able to Locate object --- " + e.getMessage());
		}
		return elementSize;

	}
	/**
	 * 
	 * @param objectref
	 * @param driver
	 * @param expectedOptions
	 * @return
	 * @throws Throwable
	 */
	public boolean validateListItems(By objectref, WebDriver driver, List<String> expectedOptions) throws Throwable

	{

		Boolean isMatched = false;

		List<String> options = new ArrayList<String>();
		try {

			LOGGER.info("Locating object " + objectref);
			
			LOGGER.info("Into findObject() :: Driver Is:" + driver);
			LOGGER.info("Into findObject() :: Object Reference Is:" + objectref);
			LOGGER.info("Into findObject() :: expectedOptions Reference Is:" + expectedOptions);

			options = getAllOptions(objectref, driver);

			if (options.equals(expectedOptions)) {
				isMatched = true;
				LOGGER.info("isMatched " + isMatched);
			} else {
				isMatched = false;
				LOGGER.info("isMatched " + isMatched);
			}

		} catch (Exception e) {
			LOGGER.warning("Not able to Locate object --- " + e.getMessage());

		}

		return isMatched;

	}
	/**
	 * 
	 * @param dropdown
	 * @param driver
	 * @return
	 */
	public List<String> getAllOptions(By dropdown, WebDriver driver) {

		List<String> options = new ArrayList<String>();
		try {
			for (WebElement option : new Select(driver.findElement(dropdown)).getOptions()) {
				String optionTxt = option.getText().trim();
				if (!optionTxt.equals(null) && !optionTxt.equals("Select Role") && !optionTxt.equals("Select Report")) {
					LOGGER.info("Options are : " + optionTxt);
					options.add(optionTxt);
				}
			}
		} catch (Exception e) {
			LOGGER.warning("Not able to Locate object --- " + e.getMessage());
		}
		LOGGER.info("options " + options);
		return options;
	}
	/**
	 * 
	 * @param objectref
	 * @param driver
	 * @param intSyncTimeOut
	 * @return
	 * @throws Throwable
	 */
	public String getElementText(By objectref, WebDriver driver, int intSyncTimeOut) throws Throwable

	{
		String elementText = null;
		try {

			LOGGER.info("Locating object " + objectref);
		
			LOGGER.info("Into findObject() :: Driver Is:" + driver);
			LOGGER.info("Into findObject() :: Object Reference Is:" + objectref);

			LOGGER.info("...................findObject Event Started........");

			WebElement element= WaitForObjectToLoadAndReturnIfExist(objectref, intSyncTimeOut, driver);

			if (element!=null){
				LOGGER.info("Object found");
				elementText = element.getText().trim();					
				if(elementText.equals("")) {
					elementText = "N/A";
				}
				LOGGER.info("Object Value --- " + elementText);
			} else{
				elementText = "No Element Found";
				LOGGER.info("No Object Found");
			}
		} catch (Exception e) {
			LOGGER.warning("Not able to Locate object --- " + e.getMessage());

		}
		return elementText;

	}
	/**
	 * 
	 * @param objectref
	 * @param driver
	 * @param attributeName
	 * @param intSyncTimeOut
	 * @return
	 * @throws Throwable
	 */
	public String getElementAttribute(By objectref, WebDriver driver, String attributeName, int intSyncTimeOut) throws Throwable {

		String elementText = "";
		try {

			LOGGER.info("Locating object " + objectref);
			LOGGER.info("Into findObject() :: Driver Is:" + driver);
			LOGGER.info("Into findObject() :: Object Reference Is:" + objectref);
			LOGGER.info("...................findObject Event Started........");

			WebElement element= WaitForObjectToLoadAndReturnIfExist(objectref, intSyncTimeOut, driver);

			if (element!=null){
				elementText = element.getAttribute(attributeName).trim();
				LOGGER.info("Object Value --- " + elementText);
			} else {
				LOGGER.info("No Object Found");
				elementText = "No Element Found";
			}
		} catch (Exception e) {
			LOGGER.warning("Not able to Locate object --- " + e.getMessage());

		}
		return elementText;

	}
	/**
	 * 
	 * @param driver
	 * @return
	 * @throws Throwable
	 */
	public String getAlertText(WebDriver driver) throws Throwable

	{
		String alertText = null;
		try {
			
			LOGGER.info("Into findObject() :: Driver Is:" + driver);
			alertText = driver.switchTo().alert().getText();
			LOGGER.info("alertText --- " + alertText);
		} catch (Exception e) {
			LOGGER.warning("Not able to Locate object --- " + e.getMessage());

		}
		return alertText;

	}
	/**
	 * 
	 * @param driver
	 * @throws Throwable
	 */
	public void alertAccess(WebDriver driver) throws Throwable

	{
		try {
			
			LOGGER.info("Into findObject() :: Driver Is:" + driver);
			driver.switchTo().alert().accept();

		} catch (Exception e) {
			LOGGER.warning("Not able to Locate object --- " + e.getMessage());

		}
	}
	/**
	 * 
	 * @param driver
	 * @throws Throwable
	 */
	public void alertDismiss(WebDriver driver) throws Throwable

	{
		try {
			
			LOGGER.info("Into findObject() :: Driver Is:" + driver);
			driver.switchTo().alert().dismiss();

		} catch (Exception e) {
			LOGGER.warning("Not able to Locate object --- " + e.getMessage());

		}
	}
	/**
	 * 
	 * @param objectref
	 * @param driver
	 * @param index
	 * @throws Throwable
	 */
	public void selectByIndex(By objectref, WebDriver driver, int index) throws Throwable

	{

		try {
			LOGGER.info("Selecting the dropdown text " + objectref);

			LOGGER.info("Into dropDown() :: Driver Is:" + driver);
			LOGGER.info("Into dropDown() :: Object Reference Is:" + objectref);
			LOGGER.info("...................Dropdown Event Started........");
			int E = driver.findElements(objectref).size();

			if (E > 0) {
				LOGGER.info("Object found");
				dropdown = new Select(driver.findElement(objectref));
				dropdown.selectByIndex(index);
			} else {
				LOGGER.info("Object Not found");
			}

			LOGGER.info("...............Dropdown Event Completed........");
		
		} catch (Exception e) {
			LOGGER.warning("Not able to Select value from the dropdown --- " + e.getMessage());

		}
	}
	/**
	 * 
	 * @param objectref
	 * @param driver
	 * @param visibleText
	 * @throws Throwable
	 */
	public void selectByVisibleText(By objectref, WebDriver driver, String visibleText) throws Throwable

	{

		try {
			LOGGER.info("Selecting the dropdown text " + objectref);

			LOGGER.info("Into dropDown() :: Driver Is:" + driver);
			LOGGER.info("Into dropDown() :: Object Reference Is:" + objectref);
			LOGGER.info("...................Dropdown Event Started........");
			int E = driver.findElements(objectref).size();
			if (E > 0) {
				LOGGER.info("Object found");
				dropdown = new Select(driver.findElement(objectref));
				dropdown.selectByVisibleText(visibleText);
			} else {
				LOGGER.info("Object Not found");
			}

			LOGGER.info("...............Dropdown Event Completed........");
		

		} catch (Exception e) {
			LOGGER.warning("Not able to Select value from the dropdown --- " + e.getMessage());

		}
	}
	/**
	 * 
	 * @param objectref
	 * @param driver
	 * @param value
	 * @throws Throwable
	 */
	public void selectByValue(By objectref, WebDriver driver, String value) throws Throwable

	{

		try {
			LOGGER.info("Selecting the dropdown text " + objectref);

		
			LOGGER.info("Into dropDown() :: Driver Is:" + driver);
			LOGGER.info("Into dropDown() :: Object Reference Is:" + objectref);
			LOGGER.info("...................Dropdown Event Started........");
			int E = driver.findElements(objectref).size();
			if (E > 0) {
				LOGGER.info("Object found");
				dropdown = new Select(driver.findElement(objectref));
				dropdown.selectByValue(value);
			} else {
				LOGGER.info("Object Not found");
			}

			LOGGER.info("...............Dropdown Event Completed........");
		

		} catch (Exception e) {
			LOGGER.warning("Not able to Select value from the dropdown --- " + e.getMessage());

		}
	}
	/**
	 * 
	 * @param objectref
	 * @param driver
	 * @param value
	 * @throws Throwable
	 */
	public void sendValueToInput(By objectref, WebDriver driver, String value) throws Throwable

	{

		try {
			LOGGER.info("Selecting the dropdown text " + objectref);

			LOGGER.info("Into dropDown() :: Driver Is:" + driver);
			LOGGER.info("Into dropDown() :: Object Reference Is:" + objectref);
			LOGGER.info("...................Dropdown Event Started........");
			int E = driver.findElements(objectref).size();
			if (E > 0) {
				LOGGER.info("Object found");
				driver.findElement(objectref).sendKeys(value);
			} else {
				LOGGER.info("Object Not found");
			}

			LOGGER.info("...............sendValueToInput Event Completed........");
		
		} catch (Exception e) {
			LOGGER.warning("Not able to find Input element --- " + e.getMessage());

		}
	}
	/**
	 * 
	 * @param driver
	 * @throws Throwable
	 */
	public void switchWindow(WebDriver driver) throws Throwable

	{
		try {
		
			LOGGER.info("Into dropDown() :: Driver Is:" + driver);
			LOGGER.info("...................switchWindow Event Started........");
			String parentWinHandle = driver.getWindowHandle();
			LOGGER.info("Parent window handle: " + parentWinHandle);
			Set<String> winHandles = driver.getWindowHandles();
			// Loop through all handles
			for (String handle : winHandles) {
				if (!handle.equals(parentWinHandle)) {
					driver.switchTo().window(handle);
					
					LOGGER.info("Title of the new window: " + driver.getTitle());
				}
			}

			LOGGER.info("...............switchWindow Event Completed........");

		} catch (Exception e) {
			LOGGER.warning("Not able to switchWindow --- " + e.getMessage());

		}
	}
	/**
	 * 
	 * @param objectref
	 * @param driver
	 * @param intSyncTimeOut
	 */
	public void javascriptClick(By objectref, WebDriver driver, int intSyncTimeOut) {
		try {
			
			LOGGER.info("Into javascriptClick() :: Driver Is:" + driver);
			LOGGER.info("Into javascriptClick() :: Object Reference Is:" + objectref);
			LOGGER.info("...................javascriptClick Event Started........");
			
			WebElement element= WaitForObjectToLoadAndReturnIfExist(objectref, intSyncTimeOut, driver);			

			if (element!=null){
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", element);
			} else {
				LOGGER.info("No Object Found");				
			}
			LOGGER.info("...............javascriptClick Event Completed........");
			

		} catch (Exception e) {
			LOGGER.warning("Not able click javascriptClick --- " + e.getMessage());

		}
	}
	
	
	/*public WebElement WaitForObjectToLoadAndReturnIfExist(By objectref, int intTimeInMillis, WebDriver driver) throws Exception {

		long t0, t1;
		boolean blnObjectExistFlag=false;
		WebElement elementReturned=null;
		try{

			WebDriverWait wait = new WebDriverWait(driver, intTimeInMillis/1000);
			wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript(
							"return document.readyState"
							).equals("complete");
				}
			});

			wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver wdriver) {
					while(true){
						if ((Boolean) ((JavascriptExecutor) wdriver).executeScript("return jQuery.active == 0")){
							break;
						}
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {}
					}
					return true;

				}
			});		

		}catch(Exception e){

		}

		try{
			t0 = System.currentTimeMillis();
			do {

				List<WebElement> ElementList=(driver.findElements(objectref));
				for (int i=0;i<ElementList.size();i++) {

					if (ElementList.get(i).isDisplayed()){
						elementReturned=ElementList.get(i);

						blnObjectExistFlag=true;
						break;
					}
				}

				ElementList.clear();
				t1 = System.currentTimeMillis();
			} while (t1 - t0 < intTimeInMillis && !blnObjectExistFlag);
		}catch(Exception e){

		}

		return elementReturned;
	}*/
	/*
	public WebElement WaitForObjectToLoadAndReturnIfExist(By objectref, int intTimeInMillis, WebDriver driver) throws Exception {
		
		WebDriverWait wait = new WebDriverWait(driver, intTimeInMillis);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(objectref));
	    return element;
	}*/
	/**
	 * 
	 * @param objectref
	 * @param timeoutInSeconds
	 * @param driver
	 * @return
	 * @throws Exception
	 */
	public WebElement WaitForObjectToLoadAndReturnIfExist(By objectref, int timeoutInSeconds, WebDriver driver) throws Exception {
		WebElement element = null;
		try {
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		element = wait.until(ExpectedConditions.presenceOfElementLocated(objectref));
		}catch(NoSuchElementException elementException) {
			elementException.printStackTrace();
		}
	    return element;
	}
}
