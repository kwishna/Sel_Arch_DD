package com.abd.testcases;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.abd.base.TestBase;
import com.abd.utilities.DataProvide;
import com.relevantcodes.extentreports.LogStatus;


public class AmazonTest extends TestBase{
	
	@Test(enabled=true, dataProviderClass=DataProvide.class, dataProvider="dataProviderForAll")
	public void amazonSearchTest(String dropdownValue, String searchData, String runMode) {
		
		if(runMode.equalsIgnoreCase("N")) {
			logger.warn("Skipping This Amazon Search Data "+searchData);
			TestBase.tes.log(LogStatus.INFO, "Test Data Is Skipped Due To Run Mode");
				throw new SkipException("Skipping This Data As Runmode Is N");
		}
		
		driver.navigate().to(or.getProperty("amazonurl"));
		logger.info("Navigating To Amazon Url"+or.getProperty("amazonurl"));
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.pollingEvery(5, TimeUnit.SECONDS)
				.withTimeout(20, TimeUnit.SECONDS)
				.withMessage("Search For Amazon Logo With ID : "+or.getProperty("logo_ID"))
				.ignoring(NoSuchElementException.class);
		
		logger.info("FluentWait For Amazon Search Box. Though, Not Required");
		@SuppressWarnings("unused")
		WebElement searchBox = wait.until(new Function<WebDriver, WebElement>(){
			@Override
			public WebElement apply(WebDriver driver) {
				WebElement searchBox = driver.findElement(By.id(or.getProperty("asearch_ID")));
				return searchBox;
			}
		});
		
				
//		WebDriverWait webwait = new WebDriverWait(driver, 20);
//		webwait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(or.getProperty("srchdropdwn_CSS")), true));
		
//		Custom Soft Assert
		verifyEquals(driver.getTitle(), "Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more" );
		logger.info("Verifying Title In Amazon "+driver.getTitle());
//		System.out.println(driver.getTitle());
		
		select("srchdropdwn_CSS",dropdownValue); logger.info("Selecting Dropdown Value In Amazon "+dropdownValue);
		enterValue("asearch_ID",searchData);	 logger.info("Entering Search Value In Amazon "+searchData);
		click("srchbtn_CSS");					 logger.info("Clicking Search Button In Amazon ");
		
	}
}
