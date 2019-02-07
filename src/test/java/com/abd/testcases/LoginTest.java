package com.abd.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.abd.base.TestBase;

public class LoginTest extends TestBase{
	
	@Test(priority=1, enabled=true)
	@Parameters("anything")
	public static void test_valid_login(String parameterFromTestNgXML) {
//		System.setProperty("org.uncommons.reportng.escape-output", "false"); //For Screenshot Attached At End Of This Test Under Reporter.log
		String s = config.getProperty("baseurl");
		driver.get(s);
		logger.info("Navigating To "+config.getProperty("baseurl"));
//		System.out.println(parameterFromTestNgXML);
		
		/* 
			driver.findElement(By.id(or.getProperty("usernameid"))).sendKeys("facebook");
			driver.findElement(By.id(or.getProperty("passwordid"))).sendKeys("*******");
			driver.navigate().to("https://pin.it");
		 */
			enterValue("fbusername_ID", "facebook");
			enterValue("fbpassword_ID", "*******");
			
			driver.navigate().to(config.getProperty("pinterest"));
			logger.info("Navigating To "+config.getProperty("pinterest"));

//		Assert.assertTrue(driver.getTitle().equalsIgnoreCase("Pinterest"));
		Assert.assertTrue(isElementPresent(By.cssSelector(or.getProperty("pinterest_CSS"))));;
		
		logger.info("Asserting Pinterest Homepage");
		verifyEquals(driver.getTitle(), "Pinterest"); //Custom Assert : If Fails, Won't Terminate The Programs
		
//		Reporter.log("Facebook Login Test");//This Will Appear In Reportng html Report
//		Reporter.log("<a target=\"_blank\" href=\"C:\\Users\\Krishna Singh\\Downloads\\Images\\galaxy.jpg\" height=100 width=100> screenshot</a>");
/*		
		try {
			Thread.sleep(2000);
			//Sleeper.SYSTEM_SLEEPER.sleep(Duration.ofSeconds(2));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
	}
}
