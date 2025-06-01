package com.tutorialsNinja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	
	@FindBy(id="input-email")
	private WebElement emailTextField;
	
	@FindBy(id="input-password")
	private WebElement passwordTextField;
	
	@FindBy(xpath="//input[@type='submit']")
	private WebElement loginBtn;
	
	@FindBy(css=".alert.alert-danger.alert-dismissible")
	private WebElement warningMessage;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterEmailAddress(String emailText)
	{
		emailTextField.sendKeys(emailText);
	}
	
	public void enterPassword(String password)
	{
		passwordTextField.sendKeys(password);
	}
	
	public AccountPage clickOnLoginButton()
	{
		loginBtn.click();
		return new AccountPage(driver);
	}
	
	public boolean getWarningMessage()
	{
		boolean getWarningMess=warningMessage.isDisplayed();
		return getWarningMess;
	}
}
