package com.yd.test.webdriver;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.yd.test.tools.ScreenShot;
import com.yd.test.tools.ReadProperties;
import com.yd.test.webdriver.Base;
import com.yd.test.webdriver.DriverBase;
import com.yd.test.webdriver.ElementBase;

public class TestBase extends DriverBase{
	public static WebDriver driver=null;
	
	public WebElement we;
	public String url="http://www.yongche.com/";
	ReadProperties readpro=new ReadProperties();
	public String cookie1;
	public String ck3s;
	public String ck1s;
	public ScreenShot screenshot=null;
	
	public TestBase(){
		
	}
	
	@BeforeTest
	public void beforeTestBase(){
		try {
			cookie1=ReadProperties.getProp("webdriver.properties", "E_1");			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		driverbase=new DriverBase();
		driver=driverbase.launch();
		maxSize(driver);
		driver.manage().timeouts().implicitlyWait(implicitlyWait ,TimeUnit.SECONDS);
		screenshot=new ScreenShot(driver);
	}
	
	@AfterTest
	public void afterTestBase(){
		
		driver.quit();
	}
}
