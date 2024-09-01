package com.ninjatutorials.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage {

	WebDriver driver;

	@FindBy(linkText = "iPhone")
	private WebElement iPhoneProductName;

	@FindBy(xpath = "//input[@id='button-search']/following-sibling::p")
	private WebElement noProductMatchedMsg;

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean getStatusOfiPhoneProductDisplayed() {
		return iPhoneProductName.isDisplayed();
	}

	public String retriveNoProductMatchedMsgText() {
		return noProductMatchedMsg.getText();
	}

}
