package com.yd.test.testcase;

import java.io.FileNotFoundException;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.yd.test.webdriver.DriverBase;
import com.yd.test.tools.ReadProperties;
import com.yd.test.tools.utils;;

public class caseTest {

	DriverBase driverbase;
	WebDriver driver;
	String url="http://www.yongche.com/";
	ReadProperties readpro=new ReadProperties();
	String cookie1;
	String ck3s;
	String ck1s;
	
	@BeforeClass
	public void init(){
		try {
			cookie1=ReadProperties.getProp("webdriver.properties", "E_1");			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
	public void test(){
		driverbase=new DriverBase();
		driver=driverbase.launch();
		driver.get("http://www.yongche.com");
		System.out.println(driver.getCurrentUrl());
	}
	
	
	@Test
	public void test01(){
		driverbase=new DriverBase();
	//	driver=driverbase.openBrowserIE();
	//	driver=driverbase.openBrowserFirefox();
		driver=driverbase.openBrowserChrome();
	//	driver=driverbase.openBrowser();
		driver.get(url);
		DriverBase.maxSize(driver);
		
	//	driver=driverbase.openWebPage(url);
		
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
	//	System.out.println(driver.getPageSource());
	//	System.out.println(driver.getClass());
		
	//	driver.manage().window().maximize();
		
		utils.wait(2);
		WebElement i=driver.findElement(By.xpath("html/body/div[1]/div[2]/div/ul/li[1]/a"));

		i.click();
		utils.wait(3);
		
		WebElement i2=driver.findElement(By.xpath("html/body/div[1]/div[2]/div/ul/li[4]/a"));
		i2.click();

		utils.wait(3);
		DriverBase.back(driver);
		utils.wait(2);
		DriverBase.forward(driver);
		utils.wait(2);
		WebElement i3=driver.findElement(By.xpath("html/body/div[1]/div[2]/div/ul/li[5]/a"));
		i3.click();
		utils.wait(3);
		
		driver.quit();
	}
	
	//@Test
	public void testcase02(){
		
		driverbase=new DriverBase();
		driver=driverbase.openBrowserChrome();
		
		//String cellphone="16830163331";
		String url="https://testing.be.yongche.org/?done=/driver/test?p=16830163331";
		driver.get(url);
		driver.manage().deleteAllCookies();
		System.out.println("cookie1:+++++++++"+cookie1);
		
		//加载cookie
		Cookie ck=new Cookie("E_1",cookie1);
		driver.manage().addCookie(ck);
		
		driver.get(url);
		DriverBase.maxSize(driver);
		utils.wait(5);
		
	//	System.out.println(driver.getPageSource());
		
	
		WebElement w1=driver.findElement(By.id("attachucp"));
		WebElement w2=w1.findElement(By.id("content"));
		driver.switchTo().frame(w2);
		String s=driver.getPageSource();
	//	System.out.println(driver.getPageSource());
		System.out.println("s11111111:"+s);
		
		s=s.split(":")[2].trim();
		s.split("<")[0].trim();
		System.out.println("s===:"+s);
			
	}
	
	@Test
	public void test03(){
		driverbase=new DriverBase();
		driver=driverbase.openBrowserChrome();
		
	//	Cookie ck11=new Cookie("B","50qwkfgcperwx1or&1&9c.13f2b13cb");
	//	Cookie ck22=new Cookie("E_3","a=0&c=@all&d=eyJuYW1lIjoiXHU1OWRhXHU5NzU5IiwicGhvbmUiOiIxMzkxMDE0NzYwMyIsImRlcHRfaWQiOjF9&e=1487255716&f=3&g=1487246916&i=10.14.26.67&n=eWFvamluZw--&t=I_J_K_M_L_N_O_H_G_A_P_Q_R_S_U_V_W_X_Y_Z_AB_AC_AE_AF_AG_AI_AJ_AL_AK_AM_AN_AO_AP_AQ_AS_BA_BZ_&u=1874&v=1&s=MEUCIQCK3uvbyKAjpGuYRb1p.QBG5wjgNnmkMTB4T6KCS..ZngIgHfwSFwOH8sGYaQM8kTujwEUXoAYOgdyXQm8G2KtIXZw-");
		Cookie ck33=new Cookie("dc_cp","7ikiq77qc8t4zg4g");
	//	Cookie ck44=new Cookie("_ga","GA1.2.1365625543.1487239482");
		String url1="http://testing.yongche.org/greencar";
		String url2="http://testing.yongche.org/greencar/driver_info.php?s=3";
		driver.get(url1);
		driver.manage().deleteAllCookies();
		
		//driver.manage().addCookie(ck11);
		//driver.manage().addCookie(ck22);
		driver.manage().addCookie(ck33);
	//	driver.manage().addCookie(ck44);
		driver.get(url2);
		Set<String> allWindowsId = driver.getWindowHandles();
		System.out.println("window size"+allWindowsId.size());
		for (String str:allWindowsId){
			
			String s=driver.switchTo().window(str).getTitle();
			System.out.println(s);
			
		}
		utils.wait(2);
	//	Alert a=driver.switchTo().alert();		
	//	utils.wait(2);
	//	a.accept();
		
		WebElement e2=driver.findElement(By.xpath("//*[@id='fileElem']"));
	//	e2.click();
		
		e2.sendKeys("E:/Git/webdriver-test/resources/testdata/data/1.jpg");
		WebElement e3=driver.findElement(By.xpath("//*[@id='personal_dummy']"));
		e3.click();
		utils.wait(2);
		
	//	Select s1=new Select(driver.findElement(By.xpath("//*[@id='personal']")));
		//Select s2=new Select(driver.findElement(By.xpath("//*[@id='personal_dummy']")));
		
		//s2.selectByValue("sh");
		
		driver.findElement(By.xpath("//*[@data-val='gz']/div")).click();
		utils.wait(1);
		driver.findElement(By.xpath("//*[@class='dwbw dwb-s']/div")).click();
		utils.wait(5);
		
		
		driver.quit();
		

	
	}
}
