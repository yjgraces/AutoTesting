package com.yd.test.tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SetProperties {

	/**
	 * @params filepath properties文件路径
	 * 
	 * 
	 */
	public static void setProperties(String filepath){
		Properties Prop = new Properties();
		
	//	String filepath=System.getProperty("user.dir")+"/resources/config/CONFIG.properties";
		try {
			FileInputStream file=new FileInputStream(filepath);
			Prop.load(file);
			file.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	//	System.setProperties(Prop);
	}
	
}
