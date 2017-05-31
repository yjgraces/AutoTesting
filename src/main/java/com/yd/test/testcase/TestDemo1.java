package com.yd.test.testcase;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.yd.test.page.Homepage;
import com.yd.test.page.YongcheHomePage;
import com.yd.test.tools.utils;
import com.yd.test.webdriver.ElementBase;
import com.yd.test.webdriver.TestBase;
import static org.assertj.core.api.Assertions.*;

import java.util.concurrent.TimeUnit;



public class TestDemo1 extends TestBase {
	public static WebDriver mydriver=null;
	public YongcheHomePage yongchehomepage;
	public ElementBase elementbase=null;
	String webSite_yongche="http://www.yongche.com";
	private Homepage h_page=null;
	
		
	@BeforeClass
	public void initPage(){	
		
		mydriver=driver;
		mydriver.get(webSite_yongche);
		yongchehomepage=new YongcheHomePage(mydriver);
	}
	
	@BeforeMethod
	public void beforemethord(){
		h_page = new Homepage(mydriver);
	}
	
	
//	@Test
	public void navigateTo2(){	
		
		ElementBase.getElement("linktext#帮助中心").click();
		delay(3);
		mydriver.navigate().back();
		ElementBase.getElement("linktext#下载客户端").click();
		delay(3);
		mydriver.navigate().back();
		ElementBase.getElement("linktext#车辆加盟").click();
		delay(3);
		mydriver.navigate().back();
		ElementBase.getElement("linktext#集团用车服务").click();
		delay(3);
	}

//	@Test
	public void navigateTo3(){			
	System.out.println(ElementBase.getElementBy("linktext", "帮助中心"));
		ElementBase.getElementBy("linktext", "帮助中心").click();			
		delay(5);	
		mydriver.navigate().back();
		delay(1);
		System.out.println(ElementBase.getElementBy("linktext", "下载客户端"));
		ElementBase.getElementBy("linktext", "下载客户端").click();		
		delay(5);
	}
	
	

//	@Test
	public void test002(){
		yongchehomepage.linktest3();		
	}
	   
//	@Test
	public void test003(){
		yongchehomepage.app_link.click();
		String title1=mydriver.getCurrentUrl();
	//	assertThat(title1).isEqualTo("https://www.yongche.com/app/").isEqualToIgnoringCase("https://www.yongche.com/app/");
		log.info(title1);
	//	assertThat(title1).isNullOrEmpty(); 
		log.info("点击‘集团用车链接’");
		screenshot.getScreenShot();
		utils.wait(3);
		yongchehomepage.jituanyongche_link.click();
		utils.wait(3);
		screenshot.getScreenShot();
		log.debug("debug log: test");
		System.out.println("log test ok");
		log.info("loginfo:输入 13910147603");
		yongchehomepage.phone.sendKeys("13910147603");
		screenshot.getScreenShot();
		utils.wait(3);
		log.error("error log:");
		mydriver.navigate().back();
		mydriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		screenshot.getScreenShot();
		utils.wait(3);
		yongchehomepage.app_link.click();
		screenshot.getScreenShot();
		utils.wait(3);
		
		log.info("yongchehomepage.app_link 属性输出");
		log.info(yongchehomepage.app_link.getLocation());
		log.info(yongchehomepage.app_link.getClass());
		log.info(yongchehomepage.app_link.getText());
		log.info(yongchehomepage.app_link.getSize());
		log.info(yongchehomepage.app_link.getTagName());
		log.info(yongchehomepage.app_link.isDisplayed());
		log.info(yongchehomepage.app_link.isEnabled());
		log.info(yongchehomepage.app_link.isSelected());
		
		
	}
	
//	@Test
	public void test04(){
		try {
			//h_page.jituanpage();
			h_page.linktest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test05(){
		yongchehomepage.cljm();
		utils.wait(3);
		mydriver.navigate().back();
		utils.wait(3);
		yongchehomepage.jituanyongche_link.click();
		utils.wait(3);
		
		
	}

}
