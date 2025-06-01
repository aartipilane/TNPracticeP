package com.tutorialsNinja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	WebDriver driver;

	@FindBy(xpath = "//a[text()='HP LP3065']")
	private WebElement checkProductName;

	@FindBy(xpath = "//p[contains(text(),'There is no product that matches the search criter')]")
	private WebElement getErrorMessage;

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean displayStatusOfValidProduct() {
		boolean productName = checkProductName.isDisplayed();
		return productName;
	}

	public boolean getErrorMessage() {
		boolean getErrorMsg = getErrorMessage.isDisplayed();
		return getErrorMsg;
	}
}
