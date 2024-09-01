package com.ninjatutorials.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;

	@FindBy(id = "input-firstname")
	private WebElement firstNameText;

	@FindBy(id = "input-lastname")
	private WebElement lastNameText;

	@FindBy(id = "input-email")
	private WebElement emailText;

	@FindBy(id = "input-telephone")
	private WebElement telephoneText;

	@FindBy(id = "input-password")
	private WebElement passwordText;

	@FindBy(id = "input-confirm")
	private WebElement confirmpasswordText;

	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement subscribeNewsLetter;

	@FindBy(name = "agree")
	private WebElement agreePrivacyPolicyRadioBtn;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement emailAlredyRegistredMsd;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement youMustAgreeprivacyPolicyWarningMsg;

	@FindBy(xpath = "//input[@name='firstname']/following-sibling::div")
	private WebElement firstNameWarningMsg;

	@FindBy(xpath = "//input[@name='lastname']/following-sibling::div")
	private WebElement lastNameWarningMsg;

	@FindBy(xpath = "//input[@name='email']/following-sibling::div")
	private WebElement emailWarningMsg;

	@FindBy(xpath = "//input[@name='telephone']/following-sibling::div")
	private WebElement telephoneWarningMsg;

	@FindBy(xpath = "//input[@name='password']/following-sibling::div")
	private WebElement passwordWarningMsg;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterFirstNameText(String firstName) {
		firstNameText.sendKeys(firstName);
	}

	public void enterlastNameText(String lastName) {
		lastNameText.sendKeys(lastName);
	}

	public void enterEmailText(String email) {
		emailText.sendKeys(email);
	}

	public void enterTelephoneText(String telephone) {
		telephoneText.sendKeys(telephone);
	}

	public void enterPasswordText(String password) {
		passwordText.sendKeys(password);
	}

	public void enterConfirmPasswordText(String confirmPassword) {
		confirmpasswordText.sendKeys(confirmPassword);
	}

	public void selectSubscribeNewsLetterRadioBtn() {
		subscribeNewsLetter.click();
	}

	public void selectPrivacyPolicy() {
		agreePrivacyPolicyRadioBtn.click();
	}

	public SuccessAccountPage clickOnContinueButton() {
		continueButton.click();
		return new SuccessAccountPage(driver);

	}

	public SuccessAccountPage registerAccount(String firstName, String lastName, String email, String telephone,
			String password, String confirmPassword) {
		firstNameText.sendKeys(firstName);
		lastNameText.sendKeys(lastName);
		emailText.sendKeys(email);
		telephoneText.sendKeys(telephone);
		passwordText.sendKeys(password);
		confirmpasswordText.sendKeys(confirmPassword);
		agreePrivacyPolicyRadioBtn.click();
		continueButton.click();
		return new SuccessAccountPage(driver);
	}

	public SuccessAccountPage registerAccountWithNewsLetter(String firstName, String lastName, String email,
			String telephone, String password, String confirmPassword) {
		firstNameText.sendKeys(firstName);
		lastNameText.sendKeys(lastName);
		emailText.sendKeys(email);
		telephoneText.sendKeys(telephone);
		passwordText.sendKeys(password);
		confirmpasswordText.sendKeys(confirmPassword);
		subscribeNewsLetter.click(); // one line changed in above method
		agreePrivacyPolicyRadioBtn.click();
		continueButton.click();
		return new SuccessAccountPage(driver);
	}

	public String retriveEmailAlreadyRegistredMsg() {
		return emailAlredyRegistredMsd.getText();
	}

	public String retriveYouMustAgreeprivacyPolicyWarningMsg() {
		return youMustAgreeprivacyPolicyWarningMsg.getText();
	}

	public String retrivefirstNameWarningMsg() {
		return firstNameWarningMsg.getText();
	}

	public String retrivelastNameWarningMsg() {
		return lastNameWarningMsg.getText();
	}

	public String retriveEmailWarningMsg() {
		return emailWarningMsg.getText();
	}

	public String retrivetelephoneWarningMsg() {
		return telephoneWarningMsg.getText();
	}

	public String retrivePasswordWarningMsg() {
		return passwordWarningMsg.getText();
	}

}
