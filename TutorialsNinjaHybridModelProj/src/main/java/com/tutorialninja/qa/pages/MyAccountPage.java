package com.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {
	
	WebDriver driver;

	public MyAccountPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	//Objects Of WebElements
	
	@FindBy(xpath="//a[text()='Edit your account information']")
	private WebElement editInformationTextField;

	
	//Actions On WebElements
	
	public Boolean confirmationTextOnAccountPage() {
		boolean confirmationFlag = editInformationTextField.isDisplayed();
		return confirmationFlag;
	}
}
