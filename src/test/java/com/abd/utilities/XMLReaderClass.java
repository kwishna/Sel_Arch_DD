package com.abd.utilities;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class XMLReaderClass {
	
	private String fileName;
	private Document doc;
	
	public XMLReaderClass(String pathOfXML) {
		this.fileName = pathOfXML;
	}
	
	public String getLocatorValue(String locator) {
		
		SAXReader reader = new SAXReader();
		try {
			doc = reader.read(fileName);
		} catch (DocumentException e) {
			
			e.printStackTrace();
		}
		
		String data = doc.selectSingleNode("//"+locator.replace(".", "/")).getText().trim();
		
		return data;
		
		// In The Test Cases 
		//driver.findElement(By.xpath(xml.getLocator("homepage.username.xpath"))).click();
	}

}
