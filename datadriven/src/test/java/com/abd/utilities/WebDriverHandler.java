package com.abd.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.abd.base.TestBase;

public class WebDriverHandler extends TestBase{
	private static WebDriver driver;
	
	public static WebDriver getDriver(String browser) {
		if(browser!=null && driver==null)
		switch(browser) {
		
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
		
		return driver;
	}
}
