package com.yd.test.webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverBase extends Base{
	public DriverBase driverbase=null;
	public WebDriver dr=null;
	public static WebDriver driver=null;
	
	//打开chrome浏览器
	public WebDriver openBrowserChrome(){
		ChromeOptions options = new ChromeOptions();	
	   options.addArguments(arg2);
	 //   options.addArguments(arg1);	    
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);	    
	    String filename=System.getProperty("user.dir")+"/resources/config/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver",filename);		
		return new ChromeDriver(options);
	}
	
	//打开firefox浏览器	
	public WebDriver openBrowserFirefox(){
		log.info("打开firefox");			
		if(browserLocation !=null){
			System.setProperty("webdriver.firefox.bin", browserLocation);
		}
        ProfilesIni allProfiles = new ProfilesIni();
        FirefoxProfile profile = new FirefoxProfile();           				
        if(!profileName.isEmpty()){
            profile = allProfiles.getProfile(profileName);
        }else if(!profilePath.isEmpty())
        {
            File profileDir = new File(profilePath);
            profile = new FirefoxProfile(profileDir);
        }
		return new FirefoxDriver(profile);	
	}
	
	//打开IE浏览器
	public WebDriver openBrowserIE(){
		String filename=System.getProperty("user.dir")+"/resources/config/IEDriverServer.exe";
		System.setProperty("webdriver.ie.driver",filename);
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);		
		return new InternetExplorerDriver();		
	}
	
	//根据配置文件启动浏览器
	public WebDriver launch(){		
		DriverBase driverbase1=new DriverBase();
		WebDriver driver1=null;
		if(browser.equalsIgnoreCase("Chrome")){	
			driver1=driverbase1.openBrowserChrome();
		}else if(browser.equalsIgnoreCase("IE")){	
			driver1=driverbase1.openBrowserIE();
		}else{
			driver1=driverbase1.openBrowserFirefox();
		}	
		
		return driver1;			
	}
	
	//通过Chrome直接打开网页并最大化
	public WebDriver openWebPage(String website){
		driverbase=new DriverBase();
		dr=driverbase.openBrowserChrome();
		dr.get(website);
		DriverBase.maxSize(dr);
		return dr;		
	}

	//网页最大化
	public static void maxSize(WebDriver dr){
		dr.manage().window().maximize();		
	}
	
	//网页back（后退）
	public static void back(WebDriver dr){
		dr.navigate().back();		
	}
	
	//网页back（前进）
	public static void forward(WebDriver dr){
		dr.navigate().forward();		
	}
	
	//网页跳转
	public static void navigateTo(WebDriver dr,String website){
		dr.navigate().to(website);			
	}


	/**
	 * 模拟粘贴文本
	 * 
	 * @param text 
	 */
	public static void paste(String text) {
		Toolkit.getDefaultToolkit().getSystemClipboard()
				.setContents(new StringSelection(text), null);
		try {
			Robot rob = new Robot();
			rob.keyPress(KeyEvent.VK_CONTROL);
			rob.keyPress(KeyEvent.VK_V);
			rob.keyRelease(KeyEvent.VK_CONTROL);
 			rob.keyRelease(KeyEvent.VK_V);
		} catch (AWTException e) {
		}
	}

	/**
	 * 模拟键盘按键操作
	 * @param key of KeyEvent 
	 * 		  e.g. pressKey(KeyEvent.VK_ENTER) 点击回车键 
	 *  
	 */
	public static void pressKey(int key) {
		try {
			Robot rob = new Robot();
			rob.keyPress(key);
			rob.keyRelease(key);
		} catch (AWTException e) {
		}
	}
	
	/**
	 * 设置等待时间 单位为秒
	 */
	public static void delay(int time) {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
