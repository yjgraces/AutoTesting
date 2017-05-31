package com.yd.test.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.yd.test.tools.WebDriverBasePage;

public class QiyePage {

    private WebDriver driver;
    private WebDriverBasePage qiyepage; 
    private Logger log=Logger.getLogger(this.getClass());
    
    public WebElement login;
    public WebElement verify_code;

   public QiyePage(WebDriver driver) {
       this.driver = driver;  
       init();
   }
   
   public void init(){
	   
	   try {
		   qiyepage=new WebDriverBasePage(driver,"qiyePage");
		   login=qiyepage.findElement("login");
		   verify_code=qiyepage.findElement("verify_code");
	   } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   }
   }
   
   
   public void qiyetest(){
	   login.sendKeys("11111");
	   verify_code.sendKeys("1234");
   }
   
}
