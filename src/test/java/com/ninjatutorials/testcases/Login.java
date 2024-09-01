package com.ninjatutorials.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ninjatutorials.base.Base;
import com.ninjatutorials.pages.AccountPage;
import com.ninjatutorials.pages.HomePage;
import com.ninjatutorials.pages.LoginPage;
import com.ninjatutorials.utils.Utilities;

public class Login extends Base {

	HomePage homePage;
	LoginPage loginPage;
	AccountPage accountPage;
	public WebDriver driver;

	public Login() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = initializBrowser(prop.getProperty("browserName"));
		homePage = new HomePage(driver);
		loginPage = homePage.navigateToLoginPage();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	// valid credentials
	@Test(priority = 1, dataProvider = "testdata")
	public void verifyLoginWithValidCredentials(String userName, String password) {
		accountPage = loginPage.login(userName, password);
		Assert.assertTrue(accountPage.getStatusOfEditYoutAccountInfoMsg());
	}

	// both invalid credentials
	@Test(priority = 2)
	public void verifyLoginWithInValidCredentials() {
		loginPage.login(Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("invlaidPassword"));
		Assert.assertEquals(loginPage.retriveEmailPassowrdNotMatchingText(),
				dataProp.getProperty("emailPassowrdNoMatchWarningMessage"));
	}

	// login with invalid email and valid password
	@Test(priority = 3)
	public void verifyLoginWithInValidEmailAndValidPassword() {
		loginPage.login(Utilities.generateEmailWithTimeStamp(), prop.getProperty("validpassword"));
		Assert.assertEquals(loginPage.retriveEmailPassowrdNotMatchingText(),
				dataProp.getProperty("emailPassowrdNoMatchWarningMessage"));
	}

	// login with valid email and invalid password
	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInValidPassword() {

		loginPage.login(prop.getProperty("validEmail"), dataProp.getProperty("invlaidPassword"));
		Assert.assertEquals(loginPage.retriveEmailPassowrdNotMatchingText(),
				dataProp.getProperty("emailPassowrdNoMatchWarningMessage"));
	}

	// login without providing credentials
	@Test(priority = 5)
	public void verifyLoginWithOutProvidingAnyCredentials() {
		loginPage.clickOnLoginButton();
		Assert.assertEquals(loginPage.retriveEmailPassowrdNotMatchingText(),
				dataProp.getProperty("emailPassowrdNoMatchWarningMessage"));
	}

	@DataProvider(name = "testdata")
	public Object[][] getTestData() {
		return Utilities.getTestDataFromExcel("Login");

	}

}
