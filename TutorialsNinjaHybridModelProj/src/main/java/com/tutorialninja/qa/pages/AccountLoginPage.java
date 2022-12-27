package com.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v106.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountLoginPage {
	
	WebDriver driver;
	
	
	public AccountLoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	//Objects Of WebElements
	@FindBy(xpath="//input[@id='input-email']")
	private WebElement emailInputBOx;
	@FindBy(xpath="//input[@id='input-password']")
	private WebElement passwordInputBox;
	@FindBy(xpath="//input[@type='submit']")
	private WebElement submittButton;
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement waringTextWhenWrongCreditials;
	
	//Actions On WebElements
	
	public void enterDataInEmailBox(String email) {
		emailInputBOx.sendKeys(email);
	}
	
	public void enterDataInPasswordBox(String password) {
		passwordInputBox.sendKeys(password);
	}
	
	public MyAccountPage clickOnSubmitButton() {
		submittButton.click();
		return new MyAccountPage(driver);
	}
	
	public MyAccountPage enterCreditialsInLoginPage(String email, String password) {
		emailInputBOx.sendKeys(email);
		passwordInputBox.sendKeys(password);
		submittButton.click();
		return new MyAccountPage(driver); 
	}
	
	public Boolean checkWaringTextMsgWhenUserEnterWrongCredentials() {
		boolean confirmationFlag = waringTextWhenWrongCreditials.isDisplayed();
		return confirmationFlag;
	}

}
