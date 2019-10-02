package com.ibm.testautomation.stepdef;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.ibm.testautomation.listener.ExtentReportListener;
import com.ibm.testautomation.pages.LoginPage;
import com.ibm.testautomation.pages.MyJuniperPage;
import com.ibm.testautomation.util.CommonUtil;
import com.ibm.testautomation.util.PropertiesFileReader;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MyJuniperStepDef extends ExtentReportListener {

	PropertiesFileReader obj = new PropertiesFileReader();
	//public static WebDriver driver = CommonUtil.getDriver();
	ExtentTest logInfo = null;
	SoftAssert softAssert = new SoftAssert();
	CommonUtil commonUtil = new CommonUtil();
	LoginPage loginPage = new LoginPage();
	MyJuniperPage myJuniperPage = new MyJuniperPage();
	private final static Logger LOGGER = Logger.getLogger(MyJuniperStepDef.class.getName());
	private WebDriver driver = null;

	/**
	 * 
	 * @param scenario
	 */
	@Before
	public void beforeScenario(Scenario scenario) {

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(true);	
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(ChromeOptions.CAPABILITY, options);
        cap.setCapability("applicationCacheEnabled", false);			
		driver = new ChromeDriver(cap);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		
		test = extent.createTest(scenario.getName());
		test = test.createNode(scenario.getName());
	}

	/**
	 * 
	 * @param scenario
	 */
	@After
	public void afterScenario(Scenario scenario) {

		if (driver != null) {
			driver.quit();
		}
	}

	/**
	 * 
	 * @throws IOException
	 */
	@Given("^Open MyJuniper Application$")
	public void open_myjuniper_application() throws IOException {
		Properties properties = obj.getProperty();

		try {

			logInfo = test.createNode(new GherkinKeyword("Given"), "Open MyJuniper Application");
			/** Open the MyJuniper Application **/
			LOGGER.info("Open the MyJuniper Application");
			loginPage.launchBrowser(System.getProperty("env"), driver);
			String expectedLoginPageTitle = properties.getProperty("loginpage.title");
			String actualLoginPageTitle = loginPage.getTitle(driver);
			Assert.assertEquals(actualLoginPageTitle, expectedLoginPageTitle, "MyJuniper Login Page is not opening!");

			logInfo.pass("Successfully open MyJuniper Login Page on - " + System.getProperty("env"));
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e.getLocalizedMessage());
			Assert.fail();
		}
	}

	/**
	 * 
	 * @throws Throwable
	 */
	@When("^Is Login success$")
	public void login_application_is_success() throws Throwable {
		Properties properties = obj.getProperty();

		try {
			logInfo = test.createNode(new GherkinKeyword("When"), "Is Login success");
			/** Login to MyJuniper Application **/
			LOGGER.info("Before Login ");
			loginPage.login(driver);
			String expectedMyJuniperPageTitle = properties.getProperty("myjuniperpage.title");
			String actualMyJuniperPageTitle = loginPage.getTitle(driver);
			LOGGER.info("After Login ");
			Assert.assertEquals(actualMyJuniperPageTitle, expectedMyJuniperPageTitle, "Login is not success!");
			System.out.println("After actualMyJuniperPageTitle ");

			logInfo.pass("Login is success");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e.getLocalizedMessage());
			Assert.fail();
		}
	}

	/**
	 * 
	 * @throws Throwable
	 */
	@Then("^Enter Customer Email$")
	public void enter_customer_email() throws Throwable {

		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), "Enter Customer Email");
			LOGGER.info("In externalLogin ");
			myJuniperPage.externalLogin(driver);
			LOGGER.info("After externalLogin ");
			// Assert.assertEquals(myJuniperPage.isOverviewExists(driver), true, "External
			// Login is not success!");
			/** Validate Email Address is Valid to see the Dashboard Records **/
			LOGGER.info("Email Address is Valid to see the Dashboard Records");
			Assert.assertEquals(myJuniperPage.isRecordExist(driver), false,
					"Email Id Exist but No Records found for Email this Email Id !!");

			logInfo.pass("External Login is success and record found");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e.getLocalizedMessage());
			Assert.fail();
		}
	}

	/**
	 * 
	 * @throws Throwable
	 */
	@Then("^Verify All Mandatory Parent Tabs are present$")
	public void verify_all_mnadatory_parent_tabs_are_present() throws Throwable {
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), "Verify All Mnadatory Parent Tabs are present");

			/** validate mandatory Tabs **/
			LOGGER.info("Validate mandatory Tabs present or not");
			Assert.assertEquals(myJuniperPage.valdateMandatoryTabs(driver), true, "Mandatory Tabs are not present !!");

			logInfo.pass("Verify All Mnadatory Parent Tabs are present - SUCCESS");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
			// Validate Each Tab data values
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e.getLocalizedMessage());
			Assert.fail();
		}
	}

	/**
	 * 
	 * @throws Throwable
	 */
	@Then("^Verify All Tabs are working$")
	public void verify_all_tabs_are_working() throws Throwable {
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"), "Verify All Tabs are working");

			/** validate all Tabs are functional **/
			/** Validate SR tab **/
			LOGGER.info("Validate Service Request tab functions");
			Assert.assertEquals(myJuniperPage.validateSRTabFunctions(driver), true,
					"Service Request Tab is not Functioning !!");

			/** Validate Products tab **/
			LOGGER.info("Validate Product tab functions");
			Assert.assertEquals(myJuniperPage.validateProductsFunctions(driver), true,
					"Products Tab is not Functioning !!");

			/** Validate Contracts tab **/
			LOGGER.info("Validate Contracts tab functions");
			Assert.assertEquals(myJuniperPage.validateContractsFunctions(driver), true,
					"Contracts Tab is not Functioning !!");

			/** Validate RenewalQuotes (if exist) Tab function **/
			LOGGER.info("Validate RenewalQuotes (if exist) Tab function");
			if (myJuniperPage.isRenewalQuotesExists(driver)) {
				Assert.assertEquals(myJuniperPage.validateRenewalQuotesFunctions(driver), true,
						"Renewal Quotes Tab is not Functioning !!");
			}

			/** Validate RMAs (if exist) Tab function **/
			LOGGER.info("Validate RMAs (if exist) Tab function");

			if (myJuniperPage.isRMAsExists(driver)) {
				Assert.assertEquals(myJuniperPage.validateRMAsFunctions(driver), true,
						"RMAs Tab is not Functioning !!");
			}

			logInfo.pass("Verify All Tabs are working - SUCCESS");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
			// Validate Each Tab data values
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e.getLocalizedMessage());
			Assert.fail();
		}
	}

	/**
	 * 
	 * @throws Throwable
	 */
	@Then("^Verify Overview Tab's data with other Mandatory Tab's data$")
	public void verify_overview_tabs_data_with_other_mandatory_tabs_data() throws Throwable {
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"),
					"Verify Overview Tab's data with other Mandatory Tab's data");

			Assert.assertEquals(myJuniperPage.verifyParentTabsValues(driver, CommonUtil.TAB_SERVICE_REQUEST), true,
					"Verify Service Request Tabs Item's Values with Overview Tab Item's Values -FAIL for Service Request Tab");

			Assert.assertEquals(myJuniperPage.verifyParentTabsValues(driver, CommonUtil.TAB_PRODUCT), true,
					"Verify Service Request Tabs Item's Values with Overview Tab Item's Values -FAIL for Products Tab");

			Assert.assertEquals(myJuniperPage.verifyParentTabsValues(driver, CommonUtil.TAB_CONTRACTS), true,
					"Verify Service Request Tabs Item's Values with Overview Tab Item's Values -FAIL for Contracts Tab");

			logInfo.pass("Verify Overview Tab's data with other Mandatory Tab's data - SUCCESS");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
			// Validate Each Tab data values
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e.getLocalizedMessage());
			Assert.fail();
		}
	}

	/**
	 * 
	 * @throws Throwable
	 */
	@Then("^Verify Mandatory Tab's data with their child Tab's data$")
	public void verify_mandatory_tabs_data_with_their_child_tabs_data() throws Throwable {
		try {
			logInfo = test.createNode(new GherkinKeyword("Then"),
					"Verify Mandatory Tab's data with their child Tab's data");

			Assert.assertEquals(myJuniperPage.verifyParentChildData(driver, CommonUtil.TAB_SERVICE_REQUEST), true,
					"Verify Mandatory Tab's data with their child Tab's data - FAIL for Service Request Tab");

			Assert.assertEquals(myJuniperPage.verifyParentChildData(driver, CommonUtil.TAB_PRODUCT), true,
					"Verify Mandatory Tab's data with their child Tab's data - FAIL for Products Tab");

			Assert.assertEquals(myJuniperPage.verifyParentChildData(driver, CommonUtil.TAB_CONTRACTS), true,
					"Verify Mandatory Tab's data with their child Tab's data - FAIL for Contracts Tab");

			logInfo.pass("Verify Mandatory Tab's data with their child Tab's data - SUCCESS");
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
			// Validate Each Tab data values
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e.getLocalizedMessage());
			Assert.fail();
		}
	}

}
