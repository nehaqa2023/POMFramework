package com.automation.pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.automation.pages.base.BasePage;

public class AccountPage extends BasePage {

	WebDriver driver;
	public static final String accountPageTitle = "Account Edit: New Account ~ Salesforce - Developer Edition";
	public static final String pageTitle = "Account: Ganya ~ Salesforce - Developer Edition";
		
	@FindBy(xpath="//input[@title='New']") WebElement newButtonElem;
	@FindBy(xpath="//input[@id='acc2']") WebElement accountNameElem;
	@FindBy(xpath="//a[@id='tryLexDialogX']") WebElement noThanksPopup;
	@FindBy(xpath="//*[@id='acc6']") WebElement techPartnerElem;
	@FindBy(xpath="//*[@id='00NDp00000CUOmH']") WebElement cpTypeElem;
	@FindBy(xpath="//td[@id='topButtonRow']/input[@name='save']") WebElement saveElem;
	
	public AccountPage(WebDriver driver) {
		super(driver);
	}

	public void clickNew() {
		clickElement(newButtonElem,"New Button");
	}
	
	
	public void enterAccountName(String accountName) {
		enterText(accountNameElem,accountName, "Account Name");
	}
	
	public void closePopup() {
		clickElement(noThanksPopup, "Close Popup");
	}
	
	public void selectTechPartner() {
		Select type = new Select(techPartnerElem);
		type.selectByValue("Technology Partner");
	}
	
	public void selectPriority(String priority) {
		Select cptype = new Select(cpTypeElem);
		cptype.selectByValue(priority);
	}
	
	public void clickSave() {
		clickElement(saveElem, "Save button");
	}
	
}
