package com.abd.base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.abd.utilities.ExcelReader;
import com.abd.utilities.ExtentReportManager;
import com.abd.utilities.PropertyReader;
import com.abd.utilities.ScreenshotUtils;
import com.abd.utilities.WebDriverHandler;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {

	/*
	 * In This Class We Will Initialize WebDriver, DB, Excel, Properties Files, Logs, Mails, Extent Report, Everything.
	 */
	private static WebElement element;
	public static WebDriver driver;
	public static Properties config;
	public static Properties or;
	public static ExcelReader excel;
	public static ExtentReports ex;
	public static ExtentTest tes;
	
	public static final Logger logger = Logger.getLogger("devpinoyLogger");
	
	@BeforeSuite
	public static void setUp() {
		try {
			config = PropertyReader.getPropertyReader("F:\\ABD\\Eclipse\\Eclipse_Works\\datadriven\\src\\test\\resources\\properties\\Configuration.properties");
			or  = PropertyReader.getPropertyReader("F:\\ABD\\Eclipse\\Eclipse_Works\\datadriven\\src\\test\\resources\\properties\\Objectrepo.properties");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ex = ExtentReportManager.getInstance(); //Initialize Extent Report
		excel = new ExcelReader(config.getProperty("excelfilepath"));
	/*	Older Code Without PropertyReader Class
		if(driver==null) {
			//Read Configuration File
			try {
				fin = new FileInputStream("F:\\ABD\\Eclipse\\Eclipse_Works\\datadriven\\src\\test\\resources\\properties\\Configuration.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				config.load(fin);
				logger.info("Config File Is Loaded");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			//Read Property File
			try {
				fin=new FileInputStream("F:\\ABD\\Eclipse\\Eclipse_Works\\datadriven\\src\\test\\resources\\properties\\Objectrepo.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				or.load(fin);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
*/	
		
			//Setting Up Drivers
			driver = WebDriverHandler.getDriver(config.getProperty("browser"));
			
/*	Older Codes When WebDriverHandler Was Not There.		
			switch(config.getProperty("browser")) {
			
			case "chrome" :// WebDriverManager.chromedriver().setup(); 
							System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
							driver = new ChromeDriver();
			break;
			
			case "firefox" :// WebDriverManager.firefoxdriver().setup();
							System.setProperty("webdriver.gecko.driver", "E:\\geckodriver.exe");
							driver = new FirefoxDriver();
			break;
			
			case "mozilla" :// WebDriverManager.firefoxdriver().setup();
							System.setProperty("webdriver.gecko.driver", "E:\\geckodriver.exe");
							driver = new FirefoxDriver();
			break;
			
			case "edge" : //WebDriverManager.edgedriver().setup();
							
							driver = new EdgeDriver();
			break;
			
			default :// WebDriverManager.chromedriver().setup();
							System.setProperty("webdriver.chrome.driver", "F:\\chromedriver.exe");
							driver = new ChromeDriver();
			}
			
*/			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			logger.info("Implicitly Wait 10 Seconds");
			driver.manage().deleteAllCookies();
		}
	
// Custom Methods To click Used. So That We Could Enter Extent Report Loggers In Each Step.	
	public static void click(String locator) {
		if(locator.contains("_CSS")) {
			element = driver.findElement(By.cssSelector(or.getProperty(locator)));
			tes.log(LogStatus.INFO, "Clicking On "+locator);
		}
		else if(locator.contains("_XPATH")) {
			driver.findElement(By.xpath(or.getProperty(locator)));
			tes.log(LogStatus.INFO, "Clicking On "+locator);
		}
		else if(locator.contains("_ID")) {
			driver.findElement(By.id(or.getProperty(locator)));
			tes.log(LogStatus.INFO, "Clicking On "+locator);
		}
			element.click();
	}
	
// Custom Methods To SendKeys Used. So That We Could Enter Extent Report Loggers In Each Step.		
	public static void enterValue(String locator, String value) {
		if(locator.contains("_CSS")) {
			element = driver.findElement(By.cssSelector(or.getProperty(locator)));
			tes.log(LogStatus.INFO, "Entering "+value+" In "+locator);
		}
		else if(locator.contains("_XPATH")) {
			element = driver.findElement(By.xpath(or.getProperty(locator)));
			tes.log(LogStatus.INFO, "Entering "+value+" In "+locator);
		}
		else if(locator.contains("_ID")) {
			element = driver.findElement(By.id(or.getProperty(locator)));	
			tes.log(LogStatus.INFO, "Entering "+value+" In "+locator);
		}
			element.clear();
			element.sendKeys(value);
	}
	
	
// Custom Methods To Select Dropdown Used. So That We Could Enter Extent Report Loggers In Each Step.		
	public static void select(String locator, String value) {
		if(locator.contains("_CSS")) {
			element = driver.findElement(By.cssSelector(or.getProperty(locator)));
			tes.log(LogStatus.INFO, "Selecting "+value+" In "+locator);
		}
		else if(locator.contains("_XPATH")) {
			element = driver.findElement(By.cssSelector(or.getProperty(locator)));
			tes.log(LogStatus.INFO, "Selecting "+value+" In "+locator);
		}
		else if(locator.contains("_ID")) {
			element = driver.findElement(By.cssSelector(or.getProperty(locator)));
			tes.log(LogStatus.INFO, "Selecting "+value+" In "+locator);
		}

			Select s = new Select(element);
//			s.selectByVisibleText(value);
			List<WebElement> web = s.getOptions();
			List<String> options = new ArrayList<String>();
			for(WebElement w :  web) {
				if(w.getText().equalsIgnoreCase(value)) {
					s.selectByVisibleText(value);
				}
				options.add(w.getText()); //option is now String value of all the Options in Drop-down
			}	
	}
	
//Codes Above Created For Extent Report	
	
	
	
//Since, AssertEquals When Fails. It Throws Exception And Terminate The Program, We Create A Custom Assert For Keep The
	//Program Going On By Catching The Exception And Showing In Report.
	public static void verifyEquals(String actual, String expected) {
		
		try {
			Assert.assertEquals(actual.trim(), expected);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//ReportNG Below
			System.setProperty("org.uncommons.reportng.escape-output", "false"); //ReportNG
			String date = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss.SSS").format(new Date()); //ScreenshotUtils
			ScreenshotUtils.takeScreenshotNow(date); //ScreenshotUtils
			Reporter.log("<br>Verification Failure<br>"+ e.getMessage());
			Reporter.log("<a target=\"_blank\" href="+ScreenshotUtils.screenshotFile+">" + "Screenshot"+"</a>");
			
			//For Extent Report
			tes.log(LogStatus.FAIL, " FAILED With Exception : "+e.getMessage()); //Extent Report
			tes.log(LogStatus.FAIL, " FAILED With Exception : "+tes.addScreenCapture(ScreenshotUtils.screenshotFile)); //Extent Report

		}
	}
	
	
	public static boolean isElementPresent(By by) {
		boolean flag = true;
		try {
			driver.findElement(by);
			tes.log(LogStatus.INFO, "Checking :: Locator is "+by + " Present");
		}
		
		catch(Exception e){
			e.printStackTrace();
			flag = false;
			logger.error("Element Not Found");
			tes.log(LogStatus.WARNING, "Locator "+by +" Not Found");
		}
		
		return flag;
	}
	
	
	@AfterSuite
	public void tearDown() {
		if(driver!=null) {
		  driver.close();
		  logger.info("Test Completed. Browser Closed");
		}
	}
}
