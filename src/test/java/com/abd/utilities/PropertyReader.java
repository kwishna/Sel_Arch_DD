package com.abd.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader{
	
	public static Properties getPropertyReader(String propertyFilePath) throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		prop.load(new FileInputStream(new File(propertyFilePath)));
		return prop;
	}
	
	public static void setPropertyReader(String propertyFilePath, String key, String value) throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		FileOutputStream fout = new FileOutputStream(new File(propertyFilePath));
		prop.setProperty(key, value);
		prop.store(fout, "New Property Added");
	}
}
