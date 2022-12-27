package com.tutorialninja.qa.utilites;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;

public class Utiles {

	
	public  static final int PAGELOAD_TIMEOUT=5;
	public static final int IMPLECITLY_WAIT=10;
	
	public static WebDriver getBrowser(WebDriver driver,String browser) {
		
		if(browser.equalsIgnoreCase("chrome")) {
			driver= new ChromeDriver();
		}else if(browser.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("ie")) {
			driver=new InternetExplorerDriver();
		}else if(browser.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		return driver;
	}
	
	public static void tearDown(WebDriver driver) {
		driver.quit();
	}
	
	public static Properties propertiesReader(String path) {
		Properties prop=new Properties();
		try {
			FileInputStream fin= new FileInputStream(path);
			try {
				prop.load(fin);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
	
	public static Object[][] excelReader(String sheetName) {
		String path=System.getProperty("user.dir")+"//src//main//java//com//tutorialninja//qa//testdata//testData.xlsx";
		Object[][] data=null;
		try {
			FileInputStream filePath= new FileInputStream(path);
			try {
				XSSFWorkbook book= new XSSFWorkbook(filePath);
				int noOfSheets = book.getNumberOfSheets();
				for(int i=0;i<noOfSheets;i++) {
					String sheet=book.getSheetName(i);
					if(sheet.equalsIgnoreCase(sheetName)) {
						XSSFSheet reqSheet = book.getSheet(sheet);
						int rowCount = reqSheet.getLastRowNum();
						int cellCount = reqSheet.getRow(0).getLastCellNum();
						data=new Object[rowCount-1][cellCount];
						for(int j=0;j<rowCount-1;j++) {
							for(int k=0;k<cellCount;k++) {
								XSSFCell cell = reqSheet.getRow(j+1).getCell(k);
								DataFormatter df= new DataFormatter();
								data[j][k]=df.formatCellValue(cell);
								
							}
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	public static String getTimeStamp() {
		Date date=new Date();
		SimpleDateFormat sdf= new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String timeStamp = sdf.format(date);
		return timeStamp;
	}
	
	public static String getScreenShot(WebDriver driver, String testcase) {
		TakesScreenshot srnshot=(TakesScreenshot)driver;
		File srcfile = srnshot.getScreenshotAs(OutputType.FILE);
		String pathOfScreenShot=System.getProperty("user.dir")+"//screenshots//"+testcase+".png";
		//File destFile= new File(pathOfScreenShot);
		try {
			FileHandler.copy(srcfile, new File(pathOfScreenShot));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pathOfScreenShot;
	}
	
	public static String generateRandomMail() {
		return "siva"+getTimeStamp()+"@gmail.com";
	}
	
	public static String generateRandomPassowrd() {
		return getTimeStamp();
	}
}
