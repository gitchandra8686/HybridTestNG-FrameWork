package com.ninjatutorials.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ninjatutorials.base.Base;
import com.ninjatutorials.pages.HomePage;
import com.ninjatutorials.pages.SearchResultPage;

public class Search extends Base {

	HomePage homePage;
	SearchResultPage searchResultPage;
	public WebDriver driver;

	public Search() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = initializBrowser(prop.getProperty("browserName"));
		homePage = new HomePage(driver);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifySearchWithExistingProductName() {
		homePage.enterTextIntoSearchBox(dataProp.getProperty("validProductName"));
		searchResultPage = homePage.clickOnSearchButton();
		Assert.assertTrue(searchResultPage.getStatusOfiPhoneProductDisplayed());
	}

	@Test(priority = 2)
	public void verifySearchWithNoNExistingProductName() {
		homePage.enterTextIntoSearchBox(dataProp.getProperty("invalidProductName"));
		searchResultPage = homePage.clickOnSearchButton();
		// Assert.assertEquals(searchResultPage.retriveNoProductMatchedMsgText(),
		// dataProp.getProperty("noProductMatched")); //intentionally failed to check
		// fail status
		Assert.assertTrue(searchResultPage.retriveNoProductMatchedMsgText().contains("wxyz"),
				"no product matched warning message should be shown");
	}

	@Test(priority = 3, dependsOnMethods = { "verifySearchWithExistingProductName",
			"verifySearchWithNoNExistingProductName" })
	public void verifySearchWithOutGivingAnyProductName() {
		searchResultPage = homePage.clickOnSearchButton();
		Assert.assertEquals(searchResultPage.retriveNoProductMatchedMsgText(),
				dataProp.getProperty("noProductMatched"));
	}
}
