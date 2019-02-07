package roughWork;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.abd.utilities.ExcelReader;
import com.abd.utilities.XMLReaderClass;

//@SuppressWarnings("unused")
public class Rough {
//		
//	private static FileInputStream fin;
//	private static XSSFWorkbook workbook;
//	private static XSSFSheet sheet;
//	private static XSSFRow row;
//	private static XSSFCell cell;
//	
//		public Rough(String excelPath) {
//			File f = new File(excelPath);
//			try {
//				fin = new FileInputStream(f);
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}
//			
//			try {
//				workbook = new XSSFWorkbook(fin);
//			} catch (IOException e) {
//				
//				e.printStackTrace();
//			}
//		}
//		
//		
//		//Get Data From Excel
//		public static Object[][] excelRead(String sheetName) {
////			new ExcelReader(config.getProperty("excelfilepath")); //Creating Object Of ExcelReader;
//			sheet = workbook.getSheet(sheetName);
//			int rowCount = sheet.getPhysicalNumberOfRows();
//			Object[][] data = new Object[rowCount-1][1];
//			Hashtable<String, String> hash = null;
//			for(int i=1; i<rowCount; i++) {
//				XSSFRow r = sheet.getRow(i);
//				int colCount =  r.getPhysicalNumberOfCells();
//				hash = new Hashtable<String, String>();
//				for(int j=0; j<colCount; j++) {
//					
//					String x = excelRead(0, j, sheetName);
//					String y = excelRead(i, j, sheetName);
//					hash.put(x, y);
////					System.out.println(hash);
//				}	
//				data[i-1][0] = hash;
//				System.out.println();
//			}
//			
//			return data;
//		}
//		
//		
//		public static String excelRead(int row, int col, String sheetName) {
//			
//			XSSFSheet s = workbook.getSheet(sheetName);
//			XSSFRow r = s.getRow(row);
//			XSSFCell c = r.getCell(col);
//			String value = c.getStringCellValue();
//			
//			return value;
//		}
//		
//		@SuppressWarnings("static-access")
//		public static void main(String[] args) {
//			@SuppressWarnings("unused")
//			Rough r = new Rough("F:\\ABD\\Eclipse\\Eclipse_Works\\datadriven\\src\\test\\resources\\excel\\data.xlsx");
//			XSSFSheet s = workbook.getSheet("YahooSignUpTest");
//			r.excelRead("YahooSignUpTest");
//	
//		}
//}
//
////		This DataProvider Is Sending Data To A Test In Another Class. For That, It Must Be A static Method
////		Commenting Below Code, When Separate DataProvider Class Is Created
////		@DataProvider(name="baba")
////		public static Object[][] dataForYahoo(){
//			
////			return(excelRead("GoogleSignUpTest"));
////		}
//		
//		
//		
//		
//		/* To Provide Data To More Than One @Test Using Single Data Provider.
//		@DataProvider(name="dynamic")
//		public static Object[][] dataForAnyTestCase(Method m) throws FileNotFoundException, IOException{
//			String methodName = m.getName();
//			System.out.println(m);
//			if(methodName.equalsIgnoreCase("login")){
//			return DataGenerator.getExcelValue("Login"); //"Login" Is SheetName
//			}
//			
//			else if(m.getName().equalsIgnoreCase("register")){
//				return DataGenerator.getExcelValue("Register"); //"Register" Is SheetName
//			}
//			
//			else{
//				return null;
//			}
//		}*/
//		
//	
//	/*
//	public static void main(String[] args) {
//		Calendar c = Calendar.getInstance();
//		System.out.println(c.getTime());
//		Rough.printx();
//	}
//	*/
	public static void main(String[] args) {
		XMLReaderClass xml = new XMLReaderClass(System.getProperty("user.dir")+"\\src\\test\\resources\\xml\\objectRepo.xml");
		System.out.println(xml.getLocatorValue("homepage.header.password.xpath"));
	}
}
