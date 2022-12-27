package com.tutorialninja.qa.test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialninja.qa.pages.AccountLoginPage;
import com.tutorialninja.qa.pages.HomePage;
import com.tutorialninja.qa.pages.MyAccountPage;
import com.tutorialninja.qa.utilites.Base;
import com.tutorialninja.qa.utilites.Utiles;

public class LoginTest extends Base {

	public WebDriver driver;
	HomePage hp;
	AccountLoginPage alp;

	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver = intializeBrowser();
		hp = new HomePage(driver);
		alp = hp.navigateToLoginPageFromHomePage();
	}

	@AfterMethod
	public void tearDown() {
		Utiles.tearDown(driver);
	}

	@Test(priority = 0,dataProvider = "setdata")
	public void verifyLoggingIntoApplicationWithValidCredentials(String username, String password) {
		MyAccountPage map = alp.enterCreditialsInLoginPage(username,password);
		Boolean confirmationtext = map.confirmationTextOnAccountPage();
		Assert.assertTrue(confirmationtext);
	}
	
	@Test(priority = 1)
	public void verifyLoggingIntoApplicationWithInValidCredentials() {
		alp.enterCreditialsInLoginPage(Utiles.generateRandomMail(),Utiles.generateRandomPassowrd());
		Boolean confirmationtext = alp.checkWaringTextMsgWhenUserEnterWrongCredentials();
		Assert.assertTrue(confirmationtext);
	}
	@Test(priority =2)
	public void verifyLoggingIntoApplicationWithOutCredentials() {
		alp.clickOnSubmitButton();
		Boolean confirmationtext = alp.checkWaringTextMsgWhenUserEnterWrongCredentials();
		Assert.assertTrue(confirmationtext);
	}
	

	@DataProvider(name = "setdata")
	public Object[][] setData() {
		Object[][] xlData = Utiles.excelReader("Sheet1");
		return xlData;
	}

}
