package com.automation.tests;

import org.testng.Assert;

import org.testng.annotations.Test;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.automation.base.BaseTest;
import com.automation.utilities.PropertyUtility;
import com.automation.pages.login.LoginPage;
import com.automation.pages.home.HomePage;
import com.automation.pages.login.ForgotPassword;

public class SalesforceLogin extends BaseTest {

	String userId;
	String password;
	String invalidUserId;
	String invalidPassword;

	void propUtil() {
		PropertyUtility pro = new PropertyUtility();
		Properties appProp = pro.loadFile("applicationDataPropertiesSF");

		userId = appProp.getProperty("login.valid.userid");
		password = appProp.getProperty("login.valid.password");
		invalidUserId = appProp.getProperty("login.invalid.userid");
		invalidPassword = appProp.getProperty("login.invalid.password");
	}

	@Test

	// TestCase1: Login Error Message-1
	public void loginErrorMessage1() {

		log.info("loginErrorMessage1()");
		propUtil();
		LoginPage loginPage = new LoginPage(driver);
		String actualTitle= loginPage.getPageTitle();
		Assert.assertEquals(actualTitle, LoginPage.loginPageTitle);
	    loginPage.enterUserName(userId);
		loginPage.clickLogin();
		String expectedMessage = "Please enter your password.";
		Assert.assertEquals(expectedMessage, loginPage.errorMessage());
		}

	// TestCase2: Login to Salesforce-2
	@Test
	public void loginToSalesforce2() {
		log.info("loginToSalesforce2()");
		propUtil();
		LoginPage loginPage = new LoginPage(driver);
		String actualTitle= loginPage.getPageTitle();
		Assert.assertEquals(actualTitle, LoginPage.loginPageTitle);
		loginPage.enterUserName(userId);
		loginPage.enterPassword(password);
		loginPage.clickLogin();
		actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, HomePage.homePageTitle);


	}

//	//TestCase3:Check Remember Me-3
	@Test
	public void checkRememberMe3() {
		log.info("checkRememberMe3()");
		propUtil();
		LoginPage loginPage = new LoginPage(driver);
		String actualTitle= loginPage.getPageTitle();
		Assert.assertEquals(actualTitle, LoginPage.loginPageTitle);
		loginPage.enterUserName(userId);
		loginPage.enterPassword(password);
        loginPage.checkRememberMe();
        loginPage.clickLogin();
        
        HomePage homePage = new HomePage(driver);
        homePage.userDropDown();
        homePage.logout();
       
		loginPage.waitUntilPageTitleContains(LoginPage.loginPageTitle);
		actualTitle = loginPage.getPageTitle();
		Assert.assertEquals(actualTitle, LoginPage.loginPageTitle);

	}

	// TestCase: Forgot Password 4A
	@Test

	public void forgotPassword4A() {
		log.info("forgotPassword4A()");

		propUtil();
		
		LoginPage loginPage = new LoginPage(driver);
		String actualTitle= loginPage.getPageTitle();
		Assert.assertEquals(actualTitle, LoginPage.loginPageTitle);
		loginPage.forgotPassword();

		ForgotPassword forgot = new ForgotPassword(driver);
		forgot.enterUserName(userId);
        forgot.continueButton();
        actualTitle= forgot.getPageTitle();
		Assert.assertEquals(actualTitle, ForgotPassword.forgotPasswordTitle);
		
}

	// TestCase: Forgot Password 4B
	@Test
	public void forgotPassword4B() {
		log.info("forgotPassword4B()");

		propUtil();
		LoginPage loginPage = new LoginPage(driver);
		String actualTitle= loginPage.getPageTitle();
		Assert.assertEquals(actualTitle, LoginPage.loginPageTitle);
		loginPage.enterUserName(invalidUserId);
		loginPage.enterPassword(invalidPassword);
		loginPage.clickLogin();
        String actualMessage = loginPage.errorMessage();
		String expectedMessage = "Please check your username and password";
		Assert.assertTrue(actualMessage.startsWith(expectedMessage));


	}
}
