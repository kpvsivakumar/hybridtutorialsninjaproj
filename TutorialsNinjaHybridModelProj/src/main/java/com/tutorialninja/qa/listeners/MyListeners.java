package com.tutorialninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialninja.qa.utilites.ExtentReporter;
import com.tutorialninja.qa.utilites.Utiles;

public class MyListeners implements ITestListener {

	ExtentReports extentReport;
	ExtentTest extentTest;
	String testName;
	
	
	@Override
	public void onStart(ITestContext context) {
		extentReport = ExtentReporter.createExtentReports();
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		testName= result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, "Test Case "+testName+"Execution is getting Started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		testName= result.getName();
		extentTest.log(Status.PASS, "Test Case "+testName+" got executed Sucessfully");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		testName = result.getName();
		WebDriver driver = null;
		Object object;
		try {
			object = result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			driver=(WebDriver)object;
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.addScreenCaptureFromPath(Utiles.getScreenShot(driver, testName));
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, "Test Case" +testName+" got Failed");
		
	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		testName=result.getName();
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, "Test Case" +testName+" got Skiped");
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		String pathOfExtentreports=ExtentReporter.pathOfReports;
		File filepathExtentreports= new File(pathOfExtentreports);
		try {
			Desktop.getDesktop().browse(filepathExtentreports.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
