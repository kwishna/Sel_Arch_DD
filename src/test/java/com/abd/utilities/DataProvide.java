package com.abd.utilities;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import com.abd.base.TestBase;

import customexception.WrongSheetNameException;

public class DataProvide extends TestBase{

	public Object[][] obj;
//	Similarly, We Can Create n Number Of Methods, Whenever Data Provider Is Required.
/*	
	@DataProvider(name="yahoosignup")
	public Object[][] yahooLogin() {
			obj= ExcelReader.excelRead("YahooSignUpTest");
		return obj;
	}
*/
	@DataProvider(name="dataProviderForAll")
	public Object[][] dataProviderForAll(Method m){
		String methodName = m.getName();
		
		if(methodName.equalsIgnoreCase("ymailSignUp")){
			obj = ExcelReader.excelRead(config.getProperty("ymaildata"));
			return obj;
		}
		
		else if(methodName.equalsIgnoreCase("amazonSearchTest")) {
			obj = ExcelReader.excelRead(config.getProperty("amazondata"));
			return obj;
		}
		
		else if(methodName.equalsIgnoreCase("ymailSignUpWithHashTable")) {
			obj = ExcelReader.excelReadWithHashtable(config.getProperty("ymaildata"));
			return obj;
		}
		
		else {
			try {
				throw new WrongSheetNameException("Excel Sheet Not Present");
			} catch (WrongSheetNameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
	}
}
