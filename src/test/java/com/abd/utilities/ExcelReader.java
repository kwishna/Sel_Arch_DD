package com.abd.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.abd.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

/*
 * This Class Reads The Excel File.
 */
public class ExcelReader extends TestBase{
	private static FileInputStream fin;
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	private static XSSFRow row;
	private static XSSFCell cell;
/**
 * When All The Methods Are Static And Variables Are Also Static There Is No Meaning Of Putting Constructor In The Class.
 * All The Methods Will Can Be Accessed By Class Name Directly. 
 * But, It Is Also Good To Put Just One Method To Initialize. As We Can Initialize It Once In Base Class And Use
 * Everywhere.
 * 	
 * @param excelPath
 */
	public static void ExcelInitializer(String excelPath) { // Earlier It Was A Constructor. One Object Was Creating In Base Class.
		File f = new File(excelPath);
		try {
			fin = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			workbook = new XSSFWorkbook(fin);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//Get Data As Object[][] From Excel
	public static Object[][] excelRead(String sheetName) {
//		new ExcelReader(config.getProperty("excelfilepath")); //Creating Object Of ExcelReader;
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getPhysicalNumberOfRows();
		Object[][] data = new Object[rowCount-1][];
		for(int i=1; i<rowCount; i++) {
			row = sheet.getRow(i);
			int cellCount = row.getPhysicalNumberOfCells();
			data[i-1] = new Object[cellCount];
			for(int j=0; j<cellCount; j++) {
				cell = row.getCell(j);
				data[i-1][j] = cell.getStringCellValue();
				//System.out.println(s);
			}	
		}
		return data;
	}
	
//Get Data As String From A Particular Cell Of A Sheet	
	public static String excelReadFromCell(int row, int col, String sheetName) {
		XSSFSheet s = workbook.getSheet(sheetName);
		XSSFRow r = s.getRow(row);
		XSSFCell c = r.getCell(col);
		String value = c.getStringCellValue();
		return value;
	}
	
// For Reading Which Test Case To Run & Which Is To Be Skipped.
// We Can Create Different Type Of Suite In Excel Sheet In Each Sheet.
// So, Pass The Required Sheet & Run The Test
	public static boolean isRunnable(String testCaseName, String sheetName) {
		boolean flag = false;
		String permisson;
		XSSFSheet s = workbook.getSheet(sheetName);
		int rowCount = s.getPhysicalNumberOfRows();
		HashMap<String, String> test = new HashMap<String, String>();
		for(int k=1; k<rowCount; k++) {
			test.put(excelReadFromCell(k, 0, sheetName), excelReadFromCell(k, 1, sheetName));
		}

		Set<String> keyset = test.keySet();
		if(keyset.contains(testCaseName)) {
			permisson = test.get(testCaseName);
			if(permisson.equalsIgnoreCase("Y")) {
				flag=true;
			}
		}
		else {
			tes.log(LogStatus.ERROR, "This Test Case Not Available In Excel Sheet");
			flag=false;
		}
		return flag;
	}
	
//  Instead Of Using Object Array. We Can Put Hashtable Array In Object[][]
	public static Object[][] excelReadWithHashtable(String sheetName) {
//		new ExcelReader(config.getProperty("excelfilepath")); //Creating Object Of ExcelReader;
//		String sheetName=config.getProperty("ymaildata");
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getPhysicalNumberOfRows();
		Object[][] data = new Object[rowCount-1][1];
		Hashtable<String, String> hash = null;
		for(int i=1; i<rowCount; i++) {
			XSSFRow r = sheet.getRow(i);
			int colCount =  r.getPhysicalNumberOfCells();
			hash = new Hashtable<String, String>();
			for(int j=0; j<colCount; j++) {
				
				String x = excelReadFromCell(0, j, sheetName);
				String y = excelReadFromCell(i, j, sheetName);
				hash.put(x, y);
//				System.out.println(hash);
			}	
			data[i-1][0] = hash;
		}
		
		return data;
	}

//	This DataProvider Is Sending Data To A Test In Another Class. For That, It Must Be A static Method
//	Commenting Below Code, When Separate DataProvider Class Is Created
//	@DataProvider(name="baba")
//	public static Object[][] dataForYahoo(){
		
//		return(excelRead("GoogleSignUpTest"));
//	}
	
	
	
	
	/* To Provide Data To More Than One @Test Using Single Data Provider.
	@DataProvider(name="dynamic")
	public static Object[][] dataForAnyTestCase(Method m) throws FileNotFoundException, IOException{
		String methodName = m.getName();
		System.out.println(m);
		if(methodName.equalsIgnoreCase("login")){
		return DataGenerator.getExcelValue("Login"); //"Login" Is SheetName
		}
		
		else if(m.getName().equalsIgnoreCase("register")){
			return DataGenerator.getExcelValue("Register"); //"Register" Is SheetName
		}
		
		else{
			return null;
		}
	}*/
	
}
