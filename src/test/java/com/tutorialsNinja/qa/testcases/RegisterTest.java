package com.tutorialsNinja.qa.testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsNinja.qa.base.Base;
import com.tutorialsNinja.qa.pages.AccountSuccessMessage;
import com.tutorialsNinja.qa.pages.HomePage;
import com.tutorialsNinja.qa.pages.RegisterPage;
import com.tutorialsNinja.qa.utils.Utilities;

public class RegisterTest extends Base {

	public WebDriver driver;
	RegisterPage rp;
	AccountSuccessMessage asm;
	
	
	public RegisterTest() {
		super();
	}

	@BeforeMethod
	public void setup() throws IOException {

		loadPropertiesFile();
		driver = initialiseBrowserAndOpenAppURL(prop.getProperty("browser"));
		HomePage hp = new HomePage(driver);
		hp.clickOnMyAccDropMenu();
		rp=hp.selectRegisterOption();
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void registerWithMAndatoryFields() {
		rp.firstNameTextField(prop1.getProperty("firstName"));
		rp.lastNameTextField(prop1.getProperty("lastName"));
		rp.emailTextField(Utilities.getDateTime() + "@gmail.com");
		rp.telphoneTextField(prop1.getProperty("telephoneNo"));
		rp.passwordTextField(prop1.getProperty("password"));
		rp.cPasswordTextField(prop1.getProperty("confirmPass"));
		rp.clickOnAgreeChkBox();
		asm=rp.clickOnContinueBtn();

		Assert.assertTrue(asm.getAccSuccessMessage(), "Account created successfully.");

	}

	@Test(priority = 2)
	public void registerWithAllFields() {

		rp.firstNameTextField(prop1.getProperty("firstName"));
		rp.lastNameTextField(prop1.getProperty("lastName"));
		rp.emailTextField(Utilities.getDateTime() + "@gmail.com");
		rp.telphoneTextField(prop1.getProperty("telephoneNo"));
		rp.passwordTextField(prop1.getProperty("password"));
		rp.cPasswordTextField(prop1.getProperty("confirmPass"));
		rp.clickOnNewsRadioBtn();
		rp.clickOnAgreeChkBox();
		asm=rp.clickOnContinueBtn();

		Assert.assertTrue(asm.getAccSuccessMessage(), "Account created successfully.");
	}

	@Test(priority = 3)
	public void registerWithDuplicatedEmail() {

		rp.firstNameTextField(prop1.getProperty("firstName"));
		rp.lastNameTextField(prop1.getProperty("lastName"));
		rp.emailTextField(prop1.getProperty("dupliEmail"));
		rp.telphoneTextField(prop1.getProperty("telephoneNo"));
		rp.passwordTextField(prop1.getProperty("password"));
		rp.cPasswordTextField(prop1.getProperty("confirmPass"));
		rp.clickOnNewsRadioBtn();
		rp.clickOnAgreeChkBox();
		asm=rp.clickOnContinueBtn();

		rp.getRegisterwarningMessage();

	}

}
