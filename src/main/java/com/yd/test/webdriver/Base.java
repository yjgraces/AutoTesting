package com.yd.test.webdriver;
import java.io.File;
import java.io.FileNotFoundException;

import org.apache.log4j.Logger;

import com.yd.test.tools.ReadProperties;

public class Base {
	public String browser;
	public String browserLocation;
	public String profileName;
	public String profilePath;
	public String webSite;
	public String arg1;
	public String arg2;
	public int implicitlyWait = 30;;
	public int webDriverWait = 30;
	public Logger log=Logger.getLogger(this.getClass());
	public Base(){
					
			try {
				browser=ReadProperties.getProp("CONFIG.properties", "WebDriver.Browser");
				browserLocation=(System.getProperty("user.dir")+ReadProperties.getProp("CONFIG.properties", "WebDriver.Browser.Location")).replace("/", File.separator);
				profileName=ReadProperties.getProp("CONFIG.properties", "WebDriver.Browser.ProfileName");
				profilePath=ReadProperties.getProp("CONFIG.properties", "WebDriver.Browser.ProfilePath");
				webSite=ReadProperties.getProp("CONFIG.properties", "WebDriver.WebSite");
				implicitlyWait=Integer.parseInt(ReadProperties.getProp("CONFIG.properties", "WebDriver.ImplicitlyWait").trim());
				webDriverWait=Integer.parseInt(ReadProperties.getProp("CONFIG.properties", "WebDriver.WebDriverWait").trim());
			    arg1=("--user-data-dir=C:/Users/"+System.getProperty("user.name")+"/AppData/Local/Google/Chrome/User Data/Default").replace("/", File.separator);
			    arg2="--test-type";
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		

}
