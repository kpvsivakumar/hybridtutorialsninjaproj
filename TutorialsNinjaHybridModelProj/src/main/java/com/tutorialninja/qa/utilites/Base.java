package com.tutorialninja.qa.utilites;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class Base {
	
	String configpath=System.getProperty("user.dir")+"//src//main//java//com//tutorialninja//qa//configproperties//config.properties";
	String testDataproppath=System.getProperty("user.dir")+"//src//main//java//com//tutorialninja//qa//testdata//testData.properties";
	public Properties configprop;
	public Properties testDataProp;
	WebDriver driver;
	public Base() {
		configprop = Utiles.propertiesReader(configpath);
		testDataProp= Utiles.propertiesReader(testDataproppath);
	}

	
	public  WebDriver intializeBrowser() {
		driver = Utiles.getBrowser(driver, configprop.getProperty("browser"));
		driver.get(configprop.getProperty("url"));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utiles.PAGELOAD_TIMEOUT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utiles.IMPLECITLY_WAIT));
		return driver;
	}
}
