package com.tutorialsNinja.qa.testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsNinja.qa.base.Base;
import com.tutorialsNinja.qa.pages.HomePage;
import com.tutorialsNinja.qa.pages.SearchPage;

public class SearchTest extends Base {

	public WebDriver driver;
	SearchPage sp;

	public SearchTest() {
		super();
	}

	@BeforeMethod

	public void setUp() throws IOException {
		loadPropertiesFile();
		driver = initialiseBrowserAndOpenAppURL(prop.getProperty("browser"));

	}

	@AfterMethod

	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void searchValidProduct() {

		HomePage hp = new HomePage(driver);
		hp.searchVProductName(prop1.getProperty("validProd"));
		sp = hp.clickOnSearchBtn();

		Assert.assertTrue(sp.displayStatusOfValidProduct(), "Product is available");

	}

	@Test(priority = 2)
	public void searchInvalidProduct() {

		HomePage hp = new HomePage(driver);
		hp.searchVProductName(prop1.getProperty("invalidProd"));
		sp = hp.clickOnSearchBtn();

		Assert.assertTrue(sp.getErrorMessage(), "There is no product that matches the search critera");
	}

	@Test(priority = 3)
	public void searchNoProduct() {

		HomePage hp = new HomePage(driver);
		hp.searchVProductName("");
		sp = hp.clickOnSearchBtn();

		Assert.assertTrue(sp.getErrorMessage(), "There is no product that matches the search critera");

	}
}
