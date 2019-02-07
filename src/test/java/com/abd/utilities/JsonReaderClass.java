package com.abd.utilities;

import java.io.File;
import java.io.IOException;

import com.jayway.jsonpath.JsonPath;

public class JsonReaderClass {
	
	private String filePath;
	private File file;
	
	
	public JsonReaderClass(String filePath) {
		this.filePath = filePath;
		file = new File(filePath);
	}
	
	public String getFromJson(String locator) throws IOException {
		
		return JsonPath.read(file, "$."+locator);
		
	}
	
//	public static void main(String[] args) throws IOException {
//		
//		JsonReaderClass json = new JsonReaderClass(System.getProperty("user.dir")+"\\src\\test\\resources\\json\\objectRepo.json");
//		System.out.println(json.getFromJson("locators.homepage.username.xpath"));
//	}
}
