package com.abd.utilities;

import java.io.File;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentReportManager {

	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentReports getInstance() {
		if(extent==null) {
			extent = new ExtentReports(System.getProperty("user.dir")+"\\surefire-reports\\html\\"+"extent.html", true, DisplayOrder.OLDEST_FIRST);
			//Report Will Be Generated in extent.html file. Overwrite TRUE
			extent.loadConfig(new File("F:\\ABD\\Eclipse\\Eclipse_Works\\datadriven\\extent_config.xml"));
			extent
            .addSystemInfo("Host Name", "AB D")
            .addSystemInfo("Environment", "Automation Testing")
            .addSystemInfo("User Name", "Baba");
		}
		return extent;
	}
	/*
	 * public  void beforeMethod(Method method) 
	{
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor("A"); //Test Script Author Name
		test.assignCategory("Sanity  :: " + env + " :: API VERSION - "+ ver); //Test Category Defined Here
	}
	 */
}
	
