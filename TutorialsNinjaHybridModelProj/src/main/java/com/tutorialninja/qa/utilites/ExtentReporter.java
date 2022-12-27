package com.tutorialninja.qa.utilites;

import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	public static String configPropPath;
	public static String testDataPropPath;
	public static String pathOfReports;

	public static ExtentReports createExtentReports() {
		ExtentReports extentReport= new ExtentReports();
		pathOfReports=System.getProperty("user.dir")+"//ExtentReports//extentreport.html";
		ExtentSparkReporter extentSporkReport=new ExtentSparkReporter(pathOfReports);
		
		extentSporkReport.config().setTheme(Theme.DARK);
		extentSporkReport.config().setDocumentTitle("TutorialNinjaReport");
		extentSporkReport.config().setReportName("TutorialNinjaAppTestCasesReport");
		extentSporkReport.config().setTimeStampFormat("dd-MM-yyyy hh_mm_ss");
		
		extentReport.attachReporter(extentSporkReport);
		
		configPropPath=System.getProperty("user.dir")+"//src//main//java//com//tutorialninja//qa//configproperties//config.properties";
		testDataPropPath=System.getProperty("user.dir")+"//src//main//java//com//tutorialninja//qa//testdata//testData.properties";
		
		Properties configProp = Utiles.propertiesReader(ExtentReporter.configPropPath);
		Properties testDataProp = Utiles.propertiesReader(ExtentReporter.testDataPropPath);
		
		extentReport.setSystemInfo("OS Name", System.getProperty("os.name"));
		extentReport.setSystemInfo("Java version", System.getProperty("java.version"));
		extentReport.setSystemInfo("User Name", System.getProperty("user.name"));
		extentReport.setSystemInfo("Name Of Browser", configProp.getProperty("browser"));
		extentReport.setSystemInfo("Application Url", configProp.getProperty("url"));
		extentReport.setSystemInfo("Email", testDataProp.getProperty("validUserName"));
		extentReport.setSystemInfo("Password", testDataProp.getProperty("validPassword"));
		
		return extentReport;
	}
}
