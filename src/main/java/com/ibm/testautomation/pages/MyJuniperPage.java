package com.ibm.testautomation.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.ibm.testautomation.util.ActionLib;
import com.ibm.testautomation.util.CommonUtil;
import com.ibm.testautomation.util.PropertiesFileReader;

public class MyJuniperPage extends BasePage {

	private By loginViewContainer = By.xpath("//section[@class='nxcsc-external-login-view-container']");
	private By custEmailInput = By.id("customer-email-address");
	private By loginReasonOpt = By.xpath("//select[@id='login-reason-opt']");
	private By externalLogin = By.id("nxcsc-btn-external-login");
	/** DASHBOARD PAGE **/
	private By overview = By.xpath("//span[text()='Overview']");
	private By serviceRequest = By.xpath("//span[text()='Service Requests']");
	private By products = By.xpath("//span[text()='Products']");
	private By contracts = By.xpath("//span[text()='Contracts']");
	private By rmas = By.xpath("//span[text()='RMAs']");
	private By renewalQuotes = By.xpath("//span[text()='Renewal Quotes']");
	/** TAB SUB ITEM **/
	/** CHILD TABS DETAIL SECTION' BUTTONS **/
	private By filterOptions = By.xpath("//button[@class='table-btn-control main-options closed']");
	private By systemDefaultView = By.xpath("//button[@class='table-btn-control savebtn closed']");
	private By pst = By.xpath("//button[@class='btnToggleTimezone table-btn-control savebtn']");
	private By actions = By.xpath("//button[contains(text(),'actions')]");
	private By refresh = By.xpath("//button[@id='nxcsc-tabbed-table-refresh']");
	private By refreshRMAs = By.xpath("//button[@id='nxcsc-tabbed-table-rma-refresh']");
	private By export = By.xpath("//button[@class='nxcsc-tabbed-table-export-btn nxcsc-export-region drop-target']");
	private By exportRenewalQuotes = By.xpath(
			"//button[@class='nxcsc-single-tabbed-table-export-btn quote-export-button nxcsc-export-region drop-target']");
	private By exportRMAs = By
			.xpath("//button[@class='nxcsc-single-tabbed-table-export-btn nxcsc-export-region drop-target']");
	/** Overview tab's child items **/
	private By overviewItemValue = By.xpath("//div[@id='nxcsc-supertab-stat-container-overview']//span[1]");
	private By overviewItemName = By.xpath("//div[@id='nxcsc-supertab-stat-container-overview']//span[2]");

	/** SR tab's child items **/
	private By srItemValue = By.xpath("//div[@id='nxcsc-supertab-stat-container-cases']//span[1]");
	private By srItemName = By.xpath("//div[@id='nxcsc-supertab-stat-container-cases']//span[2]");

	/** PRODUCT tab's child items **/
	private By productItemValue = By.xpath("//div[@id='nxcsc-supertab-stat-container-installbases']//span[1]");
	private By productItemName = By.xpath("//div[@id='nxcsc-supertab-stat-container-installbases']//span[2]");

	/** CONTRACTS tab's child items **/
	private By contractsItemValue = By.xpath("//div[@id='nxcsc-supertab-stat-container-contracts']//span[1]");
	private By contractsItemName = By.xpath("//div[@id='nxcsc-supertab-stat-container-contracts']//span[2]");

	/** ERROR MESSGAE IF NO RECORD **/
	private By noRecordErrorMsg = By.xpath("//span[@id='message']");

	/** RENEWAL QUOTES AND RMAs CHILD TAB SECTION **/
	private By childTabs = By.xpath("//div[@class='mdl-tabs__tab-bar']//a");

	/** SR, PROD, CONTRACT CHILD TAB **/
	private By primaryChildTabs = By.xpath("//nav[@class='nxcsc-tabbed-table-tabs-wrapper']//ul//li");

	/** SERVICE REQUEST CHILD TAB MATCHING VALUE FOR TotalOpenSRs **/
	private By srTotalOpenSRs = By.xpath("//nav[@class='nxcsc-tabbed-table-tabs-wrapper']//ul//li[3]//span//div//span");

	/** SERVICE REQUEST CHILD TAB MATCHING VALUE FOR OpenP1SRs **/
	private By openP1SRs = By.xpath("//nav[@class='nxcsc-tabbed-table-tabs-wrapper']//ul//li[4]//span//div//span");

	ActionLib actionLib = new ActionLib();
	CommonUtil commonUtil = new CommonUtil();
	private final static Logger LOGGER = Logger.getLogger(MyJuniperPage.class.getName());
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

	// login by Customer Email Id
	public void externalLogin(WebDriver driver) throws InterruptedException, Throwable {

		Properties properties = obj.getProperty();
		
		LOGGER.info("actionLib.isElementExist(loginViewContainer, driver) "
				+ actionLib.isElementExist(loginViewContainer, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT));

		if (actionLib.isElementExist(loginViewContainer, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT)) {

			actionLib.sendValueToInput(custEmailInput, driver, properties.getProperty("custemail"));
			actionLib.javascriptClick(loginReasonOpt, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT);
			actionLib.selectByValue(loginReasonOpt, driver, properties.getProperty("reason"));
			commonUtil.captureScreen(driver);
			actionLib.javascriptClick(externalLogin, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT);

		} else {

		}
	}

	// Check if record present for provided email address
	public boolean isRecordExist(WebDriver driver) throws Throwable {

		return actionLib.isElementExist(noRecordErrorMsg, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT);
	}

	// Validate if Overview Tab exist on Dashboard or not
	public boolean isOverviewExists(WebDriver driver) throws Throwable {

		return actionLib.isElementExist(overview, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT);
	}

	// Validate if Renewal Quotes Tab exist on Dashboard or not
	public boolean isRenewalQuotesExists(WebDriver driver) throws Throwable {

		return actionLib.isElementExist(renewalQuotes, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT);
	}

	// Validate if Renewal Quotes Tab exist on Dashboard or not
	public boolean isRMAsExists(WebDriver driver) throws Throwable {

		return actionLib.isElementExist(rmas, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT);
	}

	// Click Overview Link
	public void clickoverView(WebDriver driver) throws Throwable {
		actionLib.javascriptClick(overview, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT);
	}

	// Click Service Request Link
	public void clickSR(WebDriver driver) throws Throwable {
		actionLib.javascriptClick(serviceRequest, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT);
	}

	public void clickProducts(WebDriver driver) throws Throwable {
		actionLib.javascriptClick(products, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT);
	}

	public void clickContracts(WebDriver driver) throws Throwable {
		actionLib.javascriptClick(contracts, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT);
	}

	public void clickRMAs(WebDriver driver) throws Throwable {
		actionLib.javascriptClick(rmas, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT);
	}

	public void clickRenewalQuotes(WebDriver driver) throws Throwable {
		actionLib.javascriptClick(renewalQuotes, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT);
	}

	// validate mandatory tabs
	public boolean valdateMandatoryTabs(WebDriver driver) throws Throwable {
		boolean isMandatoryTabsPresent = false;

		if (actionLib.isElementExist(serviceRequest, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT) && actionLib.isElementExist(products, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT)
				&& actionLib.isElementExist(contracts, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT)) {
			isMandatoryTabsPresent = true;
		} else {
			isMandatoryTabsPresent = false;
		}
		return isMandatoryTabsPresent;
	}

	// validate total 5 buttons are present under each child tab or not
	public boolean validateChildButtons(WebDriver driver, String tabName) throws Throwable {
		boolean validateChildButtons = false;
		if (tabName.equalsIgnoreCase(CommonUtil.TAB_PRODUCT)) {
			if (actionLib.isElementExist(filterOptions, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT)
					&& actionLib.isElementExist(systemDefaultView, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT)
					&& actionLib.isElementExist(actions, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT)
					&& actionLib.isElementExist(export, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT)) {

				validateChildButtons = true;
			} else {
				validateChildButtons = false;
			}
		} else if (tabName.equalsIgnoreCase(CommonUtil.TAB_CONTRACTS)) {
			if (actionLib.isElementExist(filterOptions, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT)
					&& actionLib.isElementExist(systemDefaultView, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT)
					&& actionLib.isElementExist(export, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT)) {

				validateChildButtons = true;
			} else {
				validateChildButtons = false;
			}
		} else if (tabName.equalsIgnoreCase(CommonUtil.TAB_RENEWAL_QUOTES)) {
			if (actionLib.isElementExist(filterOptions, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT)
					&& actionLib.isElementExist(systemDefaultView, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT)
					&& actionLib.isElementExist(exportRenewalQuotes, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT)) {

				validateChildButtons = true;
			} else {
				validateChildButtons = false;
			}
		} else if (tabName.equalsIgnoreCase(CommonUtil.TAB_RMA)) {
			if (actionLib.isElementExist(filterOptions, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT)
					&& actionLib.isElementExist(systemDefaultView, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT)
					&& actionLib.isElementExist(exportRMAs, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT) && actionLib.isElementExist(pst, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT)
					&& actionLib.isElementExist(refreshRMAs, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT)) {

				validateChildButtons = true;
			} else {
				validateChildButtons = false;
			}
		} else {
			if (actionLib.isElementExist(filterOptions, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT)
					&& actionLib.isElementExist(systemDefaultView, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT)
					&& actionLib.isElementExist(export, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT) && actionLib.isElementExist(refresh, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT)
					&& actionLib.isElementExist(pst, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT)) {

				validateChildButtons = true;
			} else {
				validateChildButtons = false;
			}
		}
		return validateChildButtons;
	}

	// validate SR tab is working or not
	public boolean validateSRTabFunctions(WebDriver driver) throws Throwable {
		boolean validateSRTabFunctions = false;
		// click SR tab
		clickSR(driver);
		commonUtil.captureScreen(driver);
		for (int i = 2; i <= actionLib.getElementSize(primaryChildTabs, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT); i++) {
			actionLib.javascriptClick(By.xpath("//nav[@class='nxcsc-tabbed-table-tabs-wrapper']//ul//li[" + i + "]"),
					driver, CommonUtil.ELEMENT_WAIT_TIMEOUT);
			Thread.sleep(CommonUtil.ELEMENT_WAIT_TIMEOUT);
			commonUtil.captureScreen(driver);
			validateSRTabFunctions = validateChildButtons(driver, CommonUtil.TAB_SERVICE_REQUEST);
		}

		return validateSRTabFunctions;
	}

	// validate Products tab is working or not
	public boolean validateProductsFunctions(WebDriver driver) throws Throwable {
		boolean validateProductsFunctions = false;
		// click Products tab
		clickProducts(driver);
		commonUtil.captureScreen(driver);
		for (int i = 2; i <= actionLib.getElementSize(primaryChildTabs, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT); i++) {
			actionLib.javascriptClick(By.xpath("//nav[@class='nxcsc-tabbed-table-tabs-wrapper']//ul//li[" + i + "]"),
					driver, CommonUtil.ELEMENT_WAIT_TIMEOUT);
			Thread.sleep(CommonUtil.ELEMENT_WAIT_TIMEOUT);
			commonUtil.captureScreen(driver);
			validateProductsFunctions = validateChildButtons(driver, CommonUtil.TAB_PRODUCT);
		}
		return validateProductsFunctions;
	}

	// validate Contracts tab is working or not
	public boolean validateContractsFunctions(WebDriver driver) throws Throwable {
		boolean validateContractsFunctions = false;
		// click Products tab
		clickContracts(driver);
		commonUtil.captureScreen(driver);
		for (int i = 2; i <= actionLib.getElementSize(primaryChildTabs, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT); i++) {
			actionLib.javascriptClick(By.xpath("//nav[@class='nxcsc-tabbed-table-tabs-wrapper']//ul//li[" + i + "]"),
					driver, CommonUtil.ELEMENT_WAIT_TIMEOUT);
			Thread.sleep(CommonUtil.ELEMENT_WAIT_TIMEOUT);
			commonUtil.captureScreen(driver);
			validateContractsFunctions = validateChildButtons(driver, CommonUtil.TAB_CONTRACTS);
		}

		return validateContractsFunctions;
	}

	// Validate Renewal (If exist) Quotes tab is working or not
	public boolean validateRenewalQuotesFunctions(WebDriver driver) throws Throwable {
		boolean validateRenewalQuotesFunctions = false;

		clickRenewalQuotes(driver);
		commonUtil.captureScreen(driver);
		for (int i = 1; i <= actionLib.getElementSize(childTabs, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT); i++) {
			actionLib.javascriptClick(By.xpath("//div[@class='mdl-tabs__tab-bar']//a[" + i + "]"), driver, CommonUtil.ELEMENT_WAIT_TIMEOUT);
			Thread.sleep(CommonUtil.ELEMENT_WAIT_TIMEOUT);
			commonUtil.captureScreen(driver);
			validateRenewalQuotesFunctions = validateChildButtons(driver, CommonUtil.TAB_RENEWAL_QUOTES);
		}
		return validateRenewalQuotesFunctions;
	}

	// Validate RMSs (If exist) Quotes tab is working or not
	public boolean validateRMAsFunctions(WebDriver driver) throws Throwable {
		boolean validateRMAsFunctions = false;

		clickRMAs(driver);
		commonUtil.captureScreen(driver);
		for (int i = 1; i <= actionLib.getElementSize(childTabs, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT); i++) {
			actionLib.javascriptClick(By.xpath("//div[@class='mdl-tabs__tab-bar']//a[" + i + "]"), driver, CommonUtil.ELEMENT_WAIT_TIMEOUT);
			Thread.sleep(CommonUtil.ELEMENT_WAIT_TIMEOUT);
			commonUtil.captureScreen(driver);
			validateRMAsFunctions = validateChildButtons(driver, CommonUtil.TAB_RMA);
		}
		return validateRMAsFunctions;
	}

	/**
	 * GET NAME AND VALUES OF ITEMS UNDER OVERVIEW TAB
	 * 
	 * @throws Throwable
	 **/

	public HashMap<String, String> getOverviewItemNameValues(WebDriver driver) throws Throwable {
		HashMap<String, String> overviewMap = new HashMap<>();

		for (int i = 0; i < actionLib.getElementSize(overviewItemName, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT); i++) {
			LOGGER.info("In getOverviewItemNameValues Loop");
			LOGGER.info("Overview Tab Sub Name : " + driver.findElements(overviewItemName).get(i).getText());
			LOGGER.info("Overview Tab Sub Vlaues : " + driver.findElements(overviewItemValue).get(i).getText());
			overviewMap.put(driver.findElements(overviewItemName).get(i).getText(),
					driver.findElements(overviewItemValue).get(i).getText());
		}

		return overviewMap;
	}

	public HashMap<String, String> getSRItemNameValues(WebDriver driver) throws Throwable {

		HashMap<String, String> srMap = new HashMap<>();

		for (int i = 0; i < actionLib.getElementSize(srItemName, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT); i++) {
			LOGGER.info("In getSRItemNameValues Loop");
			LOGGER.info("SR Tab Sub Name : " + driver.findElements(srItemName).get(i).getText());
			LOGGER.info("SR Tab Sub Vlaues : " + driver.findElements(srItemValue).get(i).getText());
			srMap.put(driver.findElements(srItemName).get(i).getText(),
					driver.findElements(srItemValue).get(i).getText());
		}

		return srMap;
	}

	public HashMap<String, String> getProductItemNameValues(WebDriver driver) throws Throwable {
		HashMap<String, String> productMap = new HashMap<>();

		for (int i = 0; i < actionLib.getElementSize(productItemName, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT); i++) {
			LOGGER.info("In getProductItemNameValues Loop");
			LOGGER.info("Product Tab Sub Name : " + driver.findElements(productItemName).get(i).getText());
			LOGGER.info("Product Tab Sub Vlaues : " + driver.findElements(productItemValue).get(i).getText());
			productMap.put(driver.findElements(productItemName).get(i).getText(),
					driver.findElements(productItemValue).get(i).getText());
		}

		return productMap;
	}

	public HashMap<String, String> getContractItemNameValues(WebDriver driver) throws Throwable {

		HashMap<String, String> contractMap = new HashMap<>();

		for (int i = 0; i < actionLib.getElementSize(contractsItemName, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT); i++) {
			LOGGER.info("In getContractItemNameValues Loop");
			LOGGER.info("Contract Tab Sub Name : " + driver.findElements(contractsItemName).get(i).getText());
			LOGGER.info("Contract Tab Sub Vlaues : " + driver.findElements(contractsItemValue).get(i).getText());
			contractMap.put(driver.findElements(contractsItemName).get(i).getText(),
					driver.findElements(contractsItemValue).get(i).getText());
		}

		return contractMap;
	}

	// Verify Overview Tab Item values with mandatory tabs Item Values
	public boolean verifyParentTabsValues(WebDriver driver, String tabName) throws Throwable {

		boolean verifyParentTabsValues = false;
		HashMap<String, String> overviewMap = getOverviewItemNameValues(driver);

		LOGGER.info("overview Map " + overviewMap);

		if (tabName.equalsIgnoreCase(CommonUtil.TAB_SERVICE_REQUEST)) {

			HashMap<String, String> srMap = getSRItemNameValues(driver);
			LOGGER.info("SR map " + srMap);

			for (Entry<String, String> entry : srMap.entrySet()) {
				// Check if the SR tab have matching key with Overview Tab
				LOGGER.info("entry.getKey() - " + entry.getKey());
				if (overviewMap.containsKey(entry.getKey())) {

					// if yes the compare respective values of key
					LOGGER.info("overviewMap.get(entry.getKey()) - " + overviewMap.get(entry.getKey()));
					LOGGER.info("entry.getValue() - " + entry.getValue());
					if (overviewMap.get(entry.getKey()).equals(entry.getValue())) {
						verifyParentTabsValues = true;
					} else {
						verifyParentTabsValues = false;
					}
				}
			}
		} else if (tabName.equalsIgnoreCase(CommonUtil.TAB_PRODUCT)) {

			HashMap<String, String> productsMap = getProductItemNameValues(driver);
			LOGGER.info("products map " + productsMap);

			for (Entry<String, String> entry : productsMap.entrySet()) {
				// Check if the Products tab have matching key with Overview Tab
				LOGGER.info("entry.getKey() - " + entry.getKey());
				if (overviewMap.containsKey(entry.getKey())) {
					// if yes the compare respective values of key
					LOGGER.info("overviewMap.get(entry.getKey()) - " + overviewMap.get(entry.getKey()));
					LOGGER.info("entry.getValue() - " + entry.getValue());
					if (overviewMap.get(entry.getKey()).equals(entry.getValue())) {
						verifyParentTabsValues = true;
					} else {
						verifyParentTabsValues = false;
					}
				}
			}
		} else if (tabName.equalsIgnoreCase(CommonUtil.TAB_CONTRACTS)) {

			HashMap<String, String> contractMap = getContractItemNameValues(driver);
			LOGGER.info("contract map " + contractMap);

			for (Entry<String, String> entry : contractMap.entrySet()) {
				// Check if the Contracts tab have matching key with Overview Tab
				LOGGER.info("entry.getKey() - " + entry.getKey());
				if (overviewMap.containsKey(entry.getKey())) {
					// if yes the compare respective values of key
					LOGGER.info("overviewMap.get(entry.getKey()) - " + overviewMap.get(entry.getKey()));
					LOGGER.info("entry.getValue() - " + entry.getValue());
					if (overviewMap.get(entry.getKey()).equals(entry.getValue())) {
						verifyParentTabsValues = true;
					} else {
						verifyParentTabsValues = false;
					}
				}
			}
		} else {

		}
		return verifyParentTabsValues;

	}

	// Get child Tab Key Values - Mandatory Tabs
	public HashMap<String, String> getMandatoryChildItemValue(WebDriver driver, String tabName) throws Throwable {
		HashMap<String, String> mandatoryChildItemValue = new HashMap<>();
		String childTabName = null;
		String childTabValue = null;
		if (tabName.equalsIgnoreCase(CommonUtil.TAB_SERVICE_REQUEST)) {
			clickSR(driver);
		} else if (tabName.equalsIgnoreCase(CommonUtil.TAB_PRODUCT)) {
			clickProducts(driver);
		} else if (tabName.equalsIgnoreCase(CommonUtil.TAB_CONTRACTS)) {
			clickContracts(driver);
		} else {

		}
		for (int i = 2; i <= actionLib.getElementSize(primaryChildTabs, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT); i++) {
			LOGGER.info("In getMandatoryChildItemValue Loop");
			childTabName = actionLib
					.getElementText(By.xpath("//nav[@class='nxcsc-tabbed-table-tabs-wrapper']//ul//li[" + i + "]"),
							driver, CommonUtil.ELEMENT_WAIT_TIMEOUT)
					.replace("(", "=").split("=")[0].trim();
			childTabValue = actionLib
					.getElementText(By.xpath("//nav[@class='nxcsc-tabbed-table-tabs-wrapper']//ul//li[" + i + "]"),
							driver, CommonUtil.ELEMENT_WAIT_TIMEOUT)
					.replace("(", "=").split("=")[1].replace(")", "").trim();
			LOGGER.info("childTabName " + childTabName);
			LOGGER.info("childTabValue " + childTabValue);
			mandatoryChildItemValue.put(childTabName, childTabValue);
		}

		return mandatoryChildItemValue;
	}

	// Get child Tab Key Values - Secondary Tabs
	public HashMap<String, String> getSecondaryChildItemValue(WebDriver driver, String tabName) throws Throwable {
		HashMap<String, String> secondaryChildItemValue = new HashMap<>();
		String childTabName = null;
		String childTabValue = null;
		if (tabName.equalsIgnoreCase(CommonUtil.TAB_RENEWAL_QUOTES)) {
			clickRenewalQuotes(driver);
		} else if (tabName.equalsIgnoreCase(CommonUtil.TAB_RMA)) {
			clickRMAs(driver);
		} else {
		}

		for (int i = 1; i <= actionLib.getElementSize(childTabs, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT); i++) {
			LOGGER.info("In getSecondaryChildItemValue Loop");
			childTabName = actionLib
					.getElementText(By.xpath("//div[@class='mdl-tabs__tab-bar']//a[" + i + "]"), driver, CommonUtil.ELEMENT_WAIT_TIMEOUT)
					.replace("(", "=").split("=")[0];
			childTabValue = actionLib
					.getElementText(By.xpath("//div[@class='mdl-tabs__tab-bar']//a[" + i + "]"), driver, CommonUtil.ELEMENT_WAIT_TIMEOUT)
					.replace("(", "=").split("=")[1].replace(")", "");
			LOGGER.info("childTabName " + childTabName);
			LOGGER.info("childTabValue " + childTabValue);
			secondaryChildItemValue.put(childTabName, childTabValue);
		}

		return secondaryChildItemValue;
	}

	// Compare Parent and Child Tab Data
	public boolean verifyParentChildData(WebDriver driver, String tabName) throws Throwable {

		boolean verifyParentChildData = false;
		Properties properties = obj.getProperty();
		String[] parentTabItems = null;
		String[] childTabItems = null;

		if (tabName.equalsIgnoreCase(CommonUtil.TAB_SERVICE_REQUEST)) {

			clickSR(driver);

			HashMap<String, String> srMap = getSRItemNameValues(driver);
			HashMap<String, String> srChildMap = getMandatoryChildItemValue(driver, tabName);

			LOGGER.info("SR map " + srMap);
			LOGGER.info("SR child map " + srChildMap);

			parentTabItems = properties.getProperty("SRParentTab").split(",");
			childTabItems = properties.getProperty("SRChildTab").split(",");

			for (int i = 0; i < parentTabItems.length; i++) {
				LOGGER.info("parentTabItems[i] - " + parentTabItems[i].trim());
				LOGGER.info("childTabItems[i] - " + childTabItems[i].trim());
				if (srMap.containsKey(parentTabItems[i])) {

					LOGGER.info("srMap.get(parentTabItems[i]) - " + srMap.get(parentTabItems[i]));
					LOGGER.info("srChildMap.get(childTabItems[i]) - " + srChildMap.get(childTabItems[i]));

					if (srMap.get(parentTabItems[i]).equals(srChildMap.get(childTabItems[i]))) {
						verifyParentChildData = true;
					} else {
						verifyParentChildData = false;
					}
				} else {
					verifyParentChildData = false;
				}
			}

		}

		if (tabName.equalsIgnoreCase(CommonUtil.TAB_CONTRACTS)) {

			clickContracts(driver);

			HashMap<String, String> contractsMap = getContractItemNameValues(driver);
			HashMap<String, String> contractsChildMap = getMandatoryChildItemValue(driver, tabName);

			LOGGER.info("contractsMap " + contractsMap);
			LOGGER.info("contractsChildMap " + contractsChildMap);

			parentTabItems = properties.getProperty("ContractsParentTab").split(",");
			childTabItems = properties.getProperty("ContractsChildTab").split(",");

			for (int i = 0; i < parentTabItems.length; i++) {
				LOGGER.info("parentTabItems[i] - " + parentTabItems[i].trim());
				LOGGER.info("childTabItems[i] - " + childTabItems[i].trim());
				if (contractsMap.containsKey(parentTabItems[i])) {

					LOGGER.info("contractsMap.get(parentTabItems[i]) - " + contractsMap.get(parentTabItems[i]));
					LOGGER.info("contractsChildMap.get(childTabItems[i]) - " + contractsChildMap.get(childTabItems[i]));

					if (contractsMap.get(parentTabItems[i]).equals(contractsChildMap.get(childTabItems[i]))) {
						verifyParentChildData = true;
					} else {
						verifyParentChildData = false;
					}
				} else {
					verifyParentChildData = false;
				}
			}

		}

		if (tabName.equalsIgnoreCase(CommonUtil.TAB_PRODUCT)) {

			clickProducts(driver);

			HashMap<String, String> prodMap = getProductItemNameValues(driver);
			HashMap<String, String> prodChildMap = getMandatoryChildItemValue(driver, tabName);

			LOGGER.info("prodMap " + prodMap);
			LOGGER.info("prodChildMap " + prodChildMap);

			parentTabItems = properties.getProperty("ProductParentTab").split(",");
			childTabItems = properties.getProperty("ProductChildTab").split(",");

			for (int i = 0; i < parentTabItems.length; i++) {
				LOGGER.info("parentTabItems[i] - " + parentTabItems[i].trim());
				LOGGER.info("childTabItems[i] - " + childTabItems[i].trim());
				if (prodMap.containsKey(parentTabItems[i])) {

					LOGGER.info("prodMap.get(parentTabItems[i]) - " + prodMap.get(parentTabItems[i]));
					LOGGER.info("prodChildMap.get(childTabItems[i]) - " + prodChildMap.get(childTabItems[i]));

					if (prodMap.get(parentTabItems[i]).equals(prodChildMap.get(childTabItems[i]))) {
						verifyParentChildData = true;
					} else {
						verifyParentChildData = false;
					}
				} else {
					verifyParentChildData = false;
				}
			}

		}

		return verifyParentChildData;

	}

}
