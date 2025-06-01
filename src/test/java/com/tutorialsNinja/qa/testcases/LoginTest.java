package com.tutorialsNinja.qa.testcases;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsNinja.qa.base.Base;
import com.tutorialsNinja.qa.pages.AccountPage;
import com.tutorialsNinja.qa.pages.HomePage;
import com.tutorialsNinja.qa.pages.LoginPage;
import com.tutorialsNinja.qa.utils.Utilities;

public class LoginTest extends Base {

	public WebDriver driver;
	LoginPage lp;
	AccountPage ap;

	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void setup() throws IOException {

		loadPropertiesFile();
		driver = initialiseBrowserAndOpenAppURL(prop.getProperty("browser"));
		HomePage hp = new HomePage(driver);
		hp.clickOnMyAccDropMenu();
		lp=hp.selectLoginOption();

	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@DataProvider(name = "validCredentialSupplier")
	public Object[][] supplyTestData() throws InvalidFormatException, IOException {
		Object[][] data = Utilities.getTestDataFromExcelFile("Sheet1");
		return data;
	}

	@Test(priority = 1, dataProvider = "validCredentialSupplier")
	public void loginWithValidCredentials(String email, String password) {

		lp.enterEmailAddress(email);
		lp.enterPassword(password);
		ap=lp.clickOnLoginButton();

		Assert.assertTrue(ap.getConfirmationText(), "My Account - Confirmation message is displayed");

	}

	@Test(priority = 2)
	public void loginWithInvalidCredentials() {

		lp.enterEmailAddress(prop1.getProperty("invaildEmail"));
		lp.enterPassword(prop1.getProperty("invalidPass"));
		ap=lp.clickOnLoginButton();

		Assert.assertTrue(lp.getWarningMessage(), "Wrong email or password error message is displayed");

	}

	@Test(priority = 3)
	public void loginWithNoCredentials() {

		ap=lp.clickOnLoginButton();
		Assert.assertTrue(lp.getWarningMessage(), "Email or password fields are blanked");

	}
}
