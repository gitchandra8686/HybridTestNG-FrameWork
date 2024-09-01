package com.ninjatutorials.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropdown;

	@FindBy(linkText = "Login")
	private WebElement loginOption;

	@FindBy(linkText = "Register")
	private WebElement registerOption;

	@FindBy(name = "search")
	private WebElement searchBox;

	@FindBy(xpath = "//button[contains(@class,'btn-default btn-lg')]")
	private WebElement searchButton;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public LoginPage navigateToLoginPage() {
		myAccountDropdown.click();
		loginOption.click();
		return new LoginPage(driver);
	}

	public RegisterPage navigateToRegisterPage() {
		myAccountDropdown.click();
		registerOption.click();
		return new RegisterPage(driver);
	}

	public RegisterPage selectRegisterOption() {
		registerOption.click();
		return new RegisterPage(driver);
	}

	public void enterTextIntoSearchBox(String productName) {
		searchBox.sendKeys(productName);
	}

	public SearchResultPage clickOnSearchButton() {
		searchButton.click();
		return new SearchResultPage(driver);
	}
}
