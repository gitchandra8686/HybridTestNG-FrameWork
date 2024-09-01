package com.ninjatutorials.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuccessAccountPage {
	WebDriver driver;

	@FindBy(xpath = "//div[@id='content']/h1")
	private WebElement accountSuccessCreatedMessage;

	public SuccessAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String retriveAccountSuccessCreatedMsgText() {
		return accountSuccessCreatedMessage.getText();
	}
}
