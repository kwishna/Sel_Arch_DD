package com.abd.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.abd.base.TestBase;
import com.abd.utilities.ExcelReader;

public class TestHash extends TestBase {
	
	@Test(dataProvider="testhash", dataProviderClass=ExcelReader.class, enabled=true)
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

}
