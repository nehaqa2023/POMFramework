package com.automation.tests;

import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.base.BaseTest;
import com.automation.pages.home.HomePage;
import com.automation.pages.home.AccountPage;
import com.automation.pages.home.SettingsPage;
import com.automation.pages.login.LoginPage;
import com.automation.pages.home.ProfilePage;
import com.automation.utilities.PropertyUtility;

public class UserMenuDropdown extends BaseTest {
	String userId;
	String password;
	String invalidUserId;
	String invalidPassword;
	String invalidLastName;
	String postMessage;
	String accountName;
	String cptype;
	String senderName;
	String senderEmail;
	String downloadPath;


	void propUtil() {
		PropertyUtility pro = new PropertyUtility();
		Properties appProp = pro.loadFile("applicationDataPropertiesSF");

		userId = appProp.getProperty("login.valid.userid");
		password = appProp.getProperty("login.valid.password");
		invalidUserId = appProp.getProperty("login.invalid.userid");
		invalidPassword = appProp.getProperty("login.invalid.password");
		invalidLastName = appProp.getProperty("homePage.invalid.lastName");
		postMessage = appProp.getProperty("profilePage.frame.message");
		accountName = appProp.getProperty("accountPage.accountName");
		cptype = appProp.getProperty("accountPage.priority");
		senderName = appProp.getProperty("email.sender.name");
		senderEmail = appProp.getProperty("email.sender.email");
		downloadPath = appProp.getProperty("file.download.path");
	}

	// TestCase2: Login to Salesforce-2
	@Test
	public void loginToSalesforce2() {
		log.info("loginToSalesforce2()");
		propUtil();
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterUserName(userId);
		loginPage.enterPassword(password);
		loginPage.clickLogin();

	}

	// TestCase5: Select user menu for <username> drop down
	@Test
	public void selectUserMenu5() throws InterruptedException {
		log.info("selectUserMenu5()");
		propUtil();
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterUserName(userId);
		loginPage.enterPassword(password);
		loginPage.clickLogin();

		HomePage homePage = new HomePage(driver);
		homePage.userDropDown();
		Assert.assertEquals(homePage.getUserMenuItemsList(), homePage.getExpectedUserMenuItemsList());

	}

//TestCase6: Select user menu for <username> drop down
	@Test

	public void editProfile6() throws InterruptedException {
		log.info("editProfile6()");
		propUtil();
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterUserName(userId);
		loginPage.enterPassword(password);
		loginPage.clickLogin();

		HomePage homePage = new HomePage(driver);
		homePage.userDropDown();

		homePage.myProfile();

		ProfilePage profilePage = new ProfilePage(driver);

		profilePage.editDropdown();
		profilePage.editProfile();
		profilePage.switchToContactInfo();
		profilePage.aboutTab();
		profilePage.enterLastName(invalidLastName);
		profilePage.clickSaveAll();

		driver.navigate().refresh();

		profilePage.clickPostLink();
		profilePage.clickFrame();
		profilePage.postMessage(postMessage);
		profilePage.clickPublishBtn();

	}

//TestCase7:Select "My settings" option from user menu for <username> drop down
	@Test

	public void mySetting7() throws InterruptedException {
		log.info("mySetting7()");
		propUtil();
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterUserName(userId);
		loginPage.enterPassword(password);
		loginPage.clickLogin();

		HomePage homePage = new HomePage(driver);
		homePage.userDropDown();
		homePage.mySettings();

		SettingsPage sPage = new SettingsPage(driver);
		sPage.personal();
		sPage.loginHistory();
		sPage.downloadHistory();
		sPage.displayElem();
		sPage.customizeTabElem();
		sPage.selectSalesForce();
		sPage.selectReport();
		sPage.clickAddButton();
		
		sPage.saveSelectedTab();
		sPage.email();
		sPage.myEmailSettings();
		sPage.senderName(senderName);
		sPage.senderEmail(senderEmail);
		sPage.saveEmailSettings();
		sPage.acceptAlert(sPage.switchToAlert());
		
	}

//TestCase8:Select "Developers Console" option from user menu for <username> drop down
	@Test
	public void DeveloperConsole8() throws InterruptedException {
		propUtil();
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterUserName(userId);
		loginPage.enterPassword(password);
		loginPage.clickLogin();

		HomePage homePage = new HomePage(driver);
		homePage.userDropDown();
		String baseWindowHandle = driver.getWindowHandle();
		homePage.clickDeveloperConsole();
		switchToWindowOpened(baseWindowHandle);
		closeBrowser();
		switchToWindow(baseWindowHandle);
		homePage.waitUntilPageTitleContains(HomePage.homePageTitle);
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle,HomePage.homePageTitle);
	}

//TestCase9:Select "Logout" option from user menu for <username> drop down
	@Test
	public void logout9() throws InterruptedException {
		log.info("logout9()");
		propUtil();
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterUserName(userId);
		loginPage.enterPassword(password);
		loginPage.clickLogin();
		HomePage homePage = new HomePage(driver);
		homePage.userDropDown();
		homePage.logout();
		loginPage.waitUntilPageTitleContains(LoginPage.loginPageTitle);
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, LoginPage.loginPageTitle);
	}

//TestCase10:Create Account
	@Test
	public void createAccount10() {
		loginToSalesforce2();

		HomePage homePage = new HomePage(driver);
		homePage.clickAccount();

		AccountPage accountPage = new AccountPage(driver);
		accountPage.clickNew();
		accountPage.enterAccountName(accountName);
		accountPage.closePopup();
		accountPage.selectTechPartner();
		accountPage.selectPriority(cptype);
		accountPage.clickSave();
		/* accountPage.waitUntilPageTitleContains(AccountPage.pageTitle);
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, AccountPage.pageTitle); */
	}

}