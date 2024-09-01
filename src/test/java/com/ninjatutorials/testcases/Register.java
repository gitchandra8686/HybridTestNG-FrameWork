package com.ninjatutorials.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ninjatutorials.base.Base;
import com.ninjatutorials.pages.HomePage;
import com.ninjatutorials.pages.RegisterPage;
import com.ninjatutorials.pages.SuccessAccountPage;
import com.ninjatutorials.utils.Utilities;

public class Register extends Base {

	HomePage homePage;
	RegisterPage registerPage;
	SuccessAccountPage successAccountPage;
	public WebDriver driver;

	public Register() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = initializBrowser(prop.getProperty("browserName"));
		homePage = new HomePage(driver);
		registerPage = homePage.navigateToRegisterPage();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyRegisterAccountWithAllMandatoryFileds() {
		successAccountPage = registerPage.registerAccount(dataProp.getProperty("firstname"),
				dataProp.getProperty("lastname"), Utilities.generateEmailWithTimeStamp(),
				dataProp.getProperty("telephone"), prop.getProperty("validpassword"),
				prop.getProperty("validpassword"));
		Assert.assertEquals(successAccountPage.retriveAccountSuccessCreatedMsgText(),
				dataProp.getProperty("accountSuccesfullCreatedMessage"));
	}

	@Test(priority = 2)
	public void verifyRegisterAccountWithAllFileds() {
		successAccountPage = registerPage.registerAccountWithNewsLetter(dataProp.getProperty("firstname"),
				dataProp.getProperty("lastname"), Utilities.generateEmailWithTimeStamp(),
				dataProp.getProperty("telephone"), prop.getProperty("validpassword"),
				prop.getProperty("validpassword"));
		Assert.assertEquals(successAccountPage.retriveAccountSuccessCreatedMsgText(),
				dataProp.getProperty("accountSuccesfullCreatedMessage"));
	}

	@Test(priority = 3)
	public void verifyRegisterAccountWithExistingDetails() {
		registerPage.registerAccountWithNewsLetter(dataProp.getProperty("firstname"), dataProp.getProperty("lastname"),
				prop.getProperty("validEmail"), dataProp.getProperty("telephone"), prop.getProperty("validpassword"),
				prop.getProperty("validpassword"));
		Assert.assertEquals(registerPage.retriveEmailAlreadyRegistredMsg(),
				dataProp.getProperty("emailAlreadyRegisteredWarningMessage"));
	}

	@Test(priority = 4)
	public void verifyWarningsWithoutfillingAnyDetails() {
		registerPage.clickOnContinueButton();
		Assert.assertTrue(
				registerPage.retriveYouMustAgreeprivacyPolicyWarningMsg()
						.contains(dataProp.getProperty("privacypolicyWarningMessage")),
				"Privacy Policy Warning is not displayed");
		Assert.assertEquals(registerPage.retrivefirstNameWarningMsg(), dataProp.getProperty("firstNameWarningMessage"));
		Assert.assertEquals(registerPage.retrivelastNameWarningMsg(), dataProp.getProperty("lastNameWarningMessage"));
		Assert.assertEquals(registerPage.retriveEmailWarningMsg(), dataProp.getProperty("emailWarningMessage"));
		Assert.assertEquals(registerPage.retrivetelephoneWarningMsg(), dataProp.getProperty("telephoneWarningMessage"));
		Assert.assertEquals(registerPage.retrivePasswordWarningMsg(), dataProp.getProperty("passwordWarningMessage"));
	}
}
