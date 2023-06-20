package com.automation.pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.pages.base.BasePage;

public class SettingsPage extends BasePage {
	WebDriver driver;
	@FindBy(xpath = "//span[@id='PersonalInfo_font']")
	WebElement personalElem;
	@FindBy(xpath = "//span[@id='LoginHistory_font']")
	WebElement loginHistoryElem;
	@FindBy(xpath = "//div[@id='RelatedUserLoginHistoryList_body']/div/a")
	WebElement downloadHistory;
	@FindBy(xpath = "//span[@id='DisplayAndLayout_font']")
	WebElement displayElem;
	@FindBy(xpath = "//span[@id='CustomizeTabs_font']")
	WebElement customizeTabElem;
	@FindBy(xpath = "//a[@id='duel_select_0_right']")
	WebElement addButtonElem;
	@FindBy(xpath = "//*[@id='p4']")
	WebElement sfElem;
	@FindBy(xpath = "//*[@id='duel_select_0']")
	WebElement reportElem;
	@FindBy(xpath = "//*[@id='duel_select_1']")
	WebElement selectedTab;
	@FindBy(xpath = "//input[@value=' Save ']")
	WebElement saveSelectedTab;
	@FindBy(xpath = "//div[@id='EmailSetup']/a")
	WebElement email;
	@FindBy(xpath = "//a[@id='EmailSettings_font']")
	WebElement myEmailSettings;
	@FindBy(id = "senderName")
	WebElement sender_Name;
	@FindBy(id = "senderEmail")
	WebElement sender_Email;
	@FindBy(xpath = "//input[@value=' Save ']")
	WebElement saveEmailSetting;

	public SettingsPage(WebDriver driver) {
		super(driver);
	}

	public void personal() {
		clickElement(personalElem, "Personal Link");
	}

	public void loginHistory() {
		clickElement(loginHistoryElem, "LoginHistory Link");
	}

	public void downloadHistory() {
		clickElement(downloadHistory, "Download History");
	}

	public void displayElem() {
		clickElement(displayElem, "Display Link");
	}

	public void customizeTabElem() {
		clickElement(customizeTabElem, "Customize Tab");
	}

	public void clickAddButton() {
		clickElement(addButtonElem, "Add Button");
	}

	public void selectSalesForce() {
		selectByIndexData(sfElem, 8, "SalesForce");
	}

	public void selectReport() {
		if (!isReportInSelectedTab(selectedTab)) {
			selectByIndexData(reportElem, 76, "Report");
		}
	}

	public void saveSelectedTab() {
		clickElement(saveSelectedTab, "Save Selected tab");
	}

	public void email() {
		clickElement(email, "email");
	}

	public void myEmailSettings() {
		clickElement(myEmailSettings, "email settings");
	}

	public void senderName(String senderName) {
		enterText("sender_name", senderName, "Email Name");
	}

	public void senderEmail(String senderEmail) {
		enterText("sender_email", senderEmail, "Email Address");
	}

	public void saveEmailSettings() {
		clickElement(saveEmailSetting, "Save email settings");
	}

}
