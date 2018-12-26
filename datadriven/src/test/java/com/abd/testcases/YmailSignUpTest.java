package com.abd.testcases;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.abd.base.TestBase;
import com.abd.utilities.DataProvide;

public class YmailSignUpTest extends TestBase {

	
	@Test(dataProvider="dataProviderForAll", dataProviderClass=DataProvide.class, enabled=true)
	public void ymailSignUp(Object firstname, Object lastname, Object email, Object password){

		driver.get("http://mail.yahoo.com");
		logger.info("Trying To Open Yahoo Login Page");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.or(ExpectedConditions.titleContains("Yahoo"), ExpectedConditions.titleContains("Login")));
		logger.info("Yahoo Login Page Opens Succesfully!");
		
		if(TestBase.isElementPresent(By.cssSelector(or.getProperty("yahooSignUp_CSS")))==true) {
//		driver.findElement(By.cssSelector(or.getProperty("yahooSignUp"))).click();
		click("yahooSignUp_CSS");
		}
		else {
			driver.findElement(By.id("createacc")).click();
		}
		
//		driver.findElement(By.id(or.getProperty("firstname"))).sendKeys(firstname.toString());
		enterValue("firstname_ID", firstname.toString()); //changed for extent Report
		
//		driver.findElement(By.id(or.getProperty("lastname"))).sendKeys(lastname.toString());
		enterValue("lastname_ID", lastname.toString()); //changed for extent Report
		
//		driver.findElement(By.id(or.getProperty("emailid"))).sendKeys(email.toString());
		enterValue("yemail_ID", email.toString()); //changed for extent Report
		
//		driver.findElement(By.id(or.getProperty("password"))).sendKeys(password.toString());
		enterValue("ypassword_ID", password.toString()); //changed for extent Report
		
		Reporter.log("Ymail Login Test"); //This Will Appear In Reportng html Report
	}
	
	
	//Same Test As Above, But Here Parameters Comes In A HashTable. IMPORTANT!!
	@Test(dataProvider="dataProviderForAll", dataProviderClass=DataProvide.class, enabled=true)
	public void ymailSignUpWithHashTable(Hashtable<String, String> hash){
//		System.out.println(hash);
		driver.get("http://mail.yahoo.com");
		logger.info("Trying To Open Yahoo Login Page");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.or(ExpectedConditions.titleContains("Yahoo"), ExpectedConditions.titleContains("Login")));
		logger.info("Yahoo Login Page Opens Succesfully!");
		
		if(TestBase.isElementPresent(By.cssSelector(or.getProperty("yahooSignUp_CSS")))==true) {
		click("yahooSignUp_CSS");
		}
		else {
			driver.findElement(By.id("createacc")).click();
		}

		enterValue("firstname_ID", hash.get("FirstName").toString()); //Using Hashtable

		enterValue("lastname_ID", hash.get("LastName").toString()); //Using Hashtable

		enterValue("yemail_ID", hash.get("Email").toString()); //Using Hashtable

		enterValue("ypassword_ID", hash.get("Password").toString()); //Using Hashtable
		
		Reporter.log("Ymail Login Test");
	}


}
