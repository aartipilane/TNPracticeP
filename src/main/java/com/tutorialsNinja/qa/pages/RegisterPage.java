package com.tutorialsNinja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;

	@FindBy(id = "input-firstname")
	private WebElement firstNameTextField;

	@FindBy(id = "input-lastname")
	private WebElement lastNameTextField;

	@FindBy(id = "input-email")
	private WebElement emailTextField;

	@FindBy(id = "input-telephone")
	private WebElement telNoTextField;

	@FindBy(id = "input-password")
	private WebElement passwordTextField;

	@FindBy(id = "input-confirm")
	private WebElement cPasswordTextField;

	@FindBy(css = "input[value='1'][name='newsletter']")
	private WebElement newsLetterRadioBtn;

	@FindBy(name = "agree")
	private WebElement agreeChkBox;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueBtn;

	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement waringRegisterMessage;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void firstNameTextField(String firstNmF) {
		firstNameTextField.sendKeys(firstNmF);
	}

	public void lastNameTextField(String lastNmF) {
		lastNameTextField.sendKeys(lastNmF);
	}

	public void emailTextField(String emailF) {
		emailTextField.sendKeys(emailF);
	}

	public void telphoneTextField(String telNoF) {
		telNoTextField.sendKeys(telNoF);
	}

	public void passwordTextField(String passwordF) {
		passwordTextField.sendKeys(passwordF);
	}

	public void cPasswordTextField(String cPasswordF) {
		cPasswordTextField.sendKeys(cPasswordF);
	}

	public void clickOnNewsRadioBtn() {
		newsLetterRadioBtn.click();
	}

	public void clickOnAgreeChkBox() {
		agreeChkBox.click();
	}

	public AccountSuccessMessage clickOnContinueBtn() {
		continueBtn.click();
		return new AccountSuccessMessage(driver);
	}

	public boolean getRegisterwarningMessage() {
		boolean getWarningMsg = waringRegisterMessage.isDisplayed();
		return getWarningMsg;
	}
}
