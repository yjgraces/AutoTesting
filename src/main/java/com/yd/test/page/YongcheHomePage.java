package com.yd.test.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.yd.test.tools.utils;
import com.yd.test.webdriver.ElementBase;


public class YongcheHomePage extends ElementBase {	
	public YongcheHomePage(WebDriver dr) {		
		super(dr);
		webdriver=dr;
		PageFactory.initElements(wd, this);
	}
		
	 public  WebDriver webdriver;		
	 @FindBy(linkText="下载客户端")
	 public  WebElement app_link;
	 @FindBy(linkText="帮助中心")
 	 public WebElement help_link;
	 @FindBy(linkText="集团用车服务")
	 public WebElement jituanyongche_link;
	 @FindBy(linkText="车辆加盟")
	 public WebElement partner_link;
	 @FindBy(linkText="首页")
	 public WebElement homepage_link;		 
	 @FindBy(xpath="//input[@id='login']")
	 public WebElement phone;
	 @FindBy(xpath="//input[@id='verify_code']")
	 public WebElement verify_code;
	
	 public WebElement cljm_link;
	 
	 
	 public void cljm(){
		 cljm_link=ElementBase.waitGetElement("linkText#车辆加盟");
		 cljm_link.click();
		// ElementBase.click(cljm_link);
		 
		 
	 }
	
	public void linktest2(){
		app_link.click();
		utils.wait(3);
				
	}
	
	public void linktest3(){
				
		jituanyongche_link.click();
		utils.wait(3);
		phone.sendKeys("13910147603");
		utils.wait(3);
		this.webdriver.navigate().back();
		partner_link.click();
		utils.wait(3);
		this.webdriver.navigate().back();
		app_link.click();
		utils.wait(3);
	}
	

	


	
}
