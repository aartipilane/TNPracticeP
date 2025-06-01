package com.tutorialsNinja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessMessage {

	WebDriver driver;
	
	@FindBy(css="div[id='content'] h1")
	private WebElement accSuccessTestMessage;
	
	public AccountSuccessMessage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public boolean getAccSuccessMessage()
	{
		boolean getSuccessMessage = accSuccessTestMessage.isDisplayed();
		return getSuccessMessage;
	}
}
