package com.automation.base;

import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

import com.automation.utilities.*;

public class BaseTest {
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Logger log;
	protected ExtentReportsUtility report = ExtentReportsUtility.getInstance();

	@BeforeTest
	public void setUpForBeforeTest() {
		log = LogManager.getLogger(BaseTest.class.getName());

	}

	@BeforeMethod
	@Parameters("browserName")
	public void setUpBeforeTestMethod(@Optional("chrome") String browName) {
        PropertyUtility pro = new PropertyUtility();
		Properties appProp = pro.loadFile("applicationDataPropertiesSF");
		String url = appProp.getProperty("url");
		launchBrowser(browName);
		goToUrl(url);
	}

	@AfterMethod
	public void TearDownAfterTestMethod() {
		log.info("TearDownAfterTestMethod");
		driver.close();
	}

	public void launchBrowser(String browserName) {
		switch (browserName) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver,10);

			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver,10);

			break;
		case "safari":
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver,10);

			break;
		}
		log.info(browserName + " browser opened");
	}
	
	public void goToUrl(String url) {
		driver.get(url);
		log.info(url + "is entered");
	}

	public void closeBrowser() {
		driver.close();
		log.info("current browser closed");
	}




	public File getScreenshotofThePage() {
		// radom value + date()+testcasen name--›filename
		String date = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File imgFile = screenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(Constants.SCREENSHOTS_DIRECTORY_PATH + date + ".png");
		try {
			FileUtils.copyFile(imgFile, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return destFile;
		}
		return destFile;
	}
	
	public void switchToWindowOpened(String mainWindowHandle) {
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String handle : allWindowHandles) {
			if (!mainWindowHandle.equalsIgnoreCase(handle))
				driver.switchTo().window(handle);
		}
		log.info("switched to new window");
	}

	public void switchToWindow(String window) {
		driver.switchTo().window(window);
		log.info("switched to window "+window);
	}

	// go to url
	// click
	// getText
	// getattribute
	// alert
	// select- use overloading technique here
	// selectElement(int) selectElement("")

}
