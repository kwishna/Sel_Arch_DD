package com.abd.listeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.abd.base.TestBase;
import com.abd.utilities.ExcelReader;
import com.abd.utilities.ScreenshotUtils;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListener extends TestBase implements ITestListener,ISuiteListener {

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		TestBase.tes = ex.startTest(result.getName().toUpperCase()); //Extent Report
		//Below Code For Getting Which Test To Run & Which One To Skip By Reading From Excelsheet.
		if(!ExcelReader.isRunnable(result.getName(), config.getProperty("smokeTest"))) {
			TestBase.tes.log(LogStatus.INFO, result.getName()+" Is Skipped Due To Run Mode");
//			System.out.println(result.getName()+" "+ExcelReader.isRunnable(result.getName(), config.getProperty("smokeTest")));
				throw new SkipException("This Test Case Is Skipped : "+result.getName().toUpperCase());
		}
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		tes.log(LogStatus.PASS, result.getName().toUpperCase()+" PASSED"); //Extent Report
		TestBase.ex.endTest(tes); //Extent Report
		TestBase.ex.flush(); //Extent Report
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		System.setProperty("org.uncommons.reportng.escape-output", "false"); //ReportNG
		Reporter.log(result.getName()+" Failed"); //ReportNG
		String date = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss.SSS").format(new Date()); //ScreenshotUtils
		ScreenshotUtils.takeScreenshotNow(date); //ScreenshotUtils
		Reporter.log("<a target=\"_blank\" href="+ScreenshotUtils.screenshotFile+">" + "Screenshot"+"</a>"); //ReportNG
		TestBase.tes.log(LogStatus.FAIL, result.getName()+" FAILED With Exception : "+result.getThrowable()); //Extent Report
		TestBase.tes.log(LogStatus.FAIL, result.getName()+" FAILED With Exception : "+TestBase.tes.addScreenCapture(ScreenshotUtils.screenshotFile)); //Extent Report
		
		TestBase.ex.endTest(tes); //Extent Report
		TestBase.ex.flush(); //Extent Report
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		//Codes For ExtentReport
		TestBase.tes.log(LogStatus.SKIP, result.getName()+" Is Skipped");
		TestBase.ex.endTest(tes);
		TestBase.ex.flush();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		
	}
	
}
