package com.yd.test.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.FindElement;

import com.yd.test.tools.WebDriverBasePage;
import com.yd.test.tools.utils;

public class Homepage {

    private WebDriver driver;

    private WebDriverBasePage homepage=null;
    private Logger log=Logger.getLogger(this.getClass());

   public Homepage(WebDriver driver) {
       this.driver = driver;
     
   }
   
   public void jituanpage() throws Exception{
	   homepage=new WebDriverBasePage(driver,"homePage");
	  
	   log.info("打开 集团用车服务页面");
	   homepage.findElement("jituan").click();
	   utils.wait(3);

   }
   
   public void linktest() throws Exception{
       new WebDriverBasePage(driver,"homePage").click("jituan"); 
       utils.wait(3);
       new WebDriverBasePage(driver,"qiyePage").click("login");
      
       utils.wait(3);
       driver.navigate().back();
       utils.wait(3);
       
   }

}
