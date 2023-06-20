package com.automation.pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.automation.pages.base.BasePage;

public class ProfilePage extends BasePage{
		
	WebDriver driver;
	@FindBy(xpath="//a[@id='moderatorMutton']") WebElement editDropdown;
	@FindBy(xpath="//a[@title='Edit Profile']") WebElement editProfile;
	//@FindBy(xpath="//a[@class='contactInfoLaunch editLink']/img") WebElement editLinkElem;
    @FindBy(xpath="//li[@id='aboutTab']/a[contains(@aria-controls,'Body:1')]") WebElement aboutTabElem;
    @FindBy(id="contactInfoContentId") WebElement frameContactInfoElem;
    @FindBy(xpath="//input[@id=\"lastName\"]") WebElement lastNameElem;
    @FindBy(xpath="//input[@value=\"Save All\"]") WebElement saveAllElem;
    @FindBy(xpath="//*[@id=\"publisherAttachTextPost\"]") WebElement postLinkElem;
    @FindBy(xpath="//*[@id='cke_43_contents']/iframe") WebElement frameElem;
    @FindBy(id="publishersharebutton") WebElement publishButton;
    
    public ProfilePage(WebDriver driver) {
		super(driver);
	}
    public void editDropdown() {
    	clickElement(editDropdown, "Edit dropdown");
    }
    
    public void editProfile() {
    	clickElement(editProfile,"Edit Link");
    }

	public void aboutTab() {
		clickElement(aboutTabElem, "About Tab");
	}

	public void switchToContactInfo() {
		switchToFrame(frameContactInfoElem, "Contact Info");
	}

	public void enterLastName(String lastName) {
		enterText(lastNameElem, lastName, "Last Name" );
	}

	public void clickSaveAll() {
		clickElement(saveAllElem, "Save All");
	}
	
	public void clickPostLink() {
		clickElement(postLinkElem, "Post Link");
	}

	public void clickFrame() {
		clickElement(frameElem, "Frame");
	}

	public void postMessage(String postMessage) {
		actionSendKeys(postMessage);
	}

	public void clickPublishBtn() {
		clickElement(publishButton, "Publish Share Button");		
	}
    

}
