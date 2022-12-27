package com.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v106.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	WebDriver driver;

	//Objects Of WebElements
	
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountField;
	@FindBy(xpath="//a[text()='Login']")
	private WebElement loginOption;
	
	
	
	//Actions On WebElements
	
	public void clickOnmyAccountOption() {
		myAccountField.click();
	}
	
	public AccountLoginPage clickOnLoginOption() {
		loginOption.click();
		return new AccountLoginPage(driver);
	}
	public AccountLoginPage navigateToLoginPageFromHomePage() {
		myAccountField.click();
		loginOption.click();
		return new AccountLoginPage(driver);
	}
}
