package com.abd.utilities;

import java.io.File;

import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.abd.base.TestBase;

public class ScreenshotUtils extends TestBase{
	private static String screenshotName;
	private final static String screenshotPath = "F:\\ABD\\Eclipse\\Eclipse_Works\\datadriven\\src\\test\\resources\\screenshots\\";
	public static String screenshotFile;
	
	
	public static void takeScreenshotNow(String l) {
	String name = l.toString();
//	String screenshotPath = config.getProperty("screenshotpath");
	screenshotName = "Bug_" + name + ".jpeg";
	File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	logger.error("Capturing Screenshot. Test Case Failed");
	screenshotFile = screenshotPath+screenshotName;
	File destFile = new File(screenshotFile);
	try {
		FileUtils.copyFile(srcFile, destFile);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
/*
  	public String getCurrentLocalDateTimeStamp() {
    return LocalDateTime.now()
       .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
}
 */
