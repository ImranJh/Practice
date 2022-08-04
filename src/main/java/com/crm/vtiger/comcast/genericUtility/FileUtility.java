package com.crm.vtiger.comcast.genericUtility;

import java.io.FileInputStream;
import java.util.Properties;

public class FileUtility {
	public String accessPropertyData (String key) throws Throwable {
	
		FileInputStream fis= new FileInputStream("src/main/resources/Commondata/commondata.properties");
		Properties pobj= new Properties();
		pobj.load(fis);
		String value = pobj.getProperty(key);
		return value;
		
	}
}
