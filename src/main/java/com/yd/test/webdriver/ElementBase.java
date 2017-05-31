package com.yd.test.webdriver;

import java.io.FileNotFoundException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.yd.test.tools.*;

public class ElementBase {
	public static  WebDriver wd=null;
	public static  WebElement we=null;
	public static Logger log=Logger.getLogger(ElementBase.class);
	public static String type;
	public static String locator;
	
	
	public ElementBase(WebDriver webdriver){
		wd=webdriver;
		PageFactory.initElements(wd, this);
	}	
	public static WebElement getElementBy(String type,String locator){
		//WebElement we=null;
		System.out.println("元素定位====type:"+type+"===locator:"+locator);
		
		if (type.equalsIgnoreCase("linkText")){
			we=wd.findElement(By.linkText(locator));
		}else if(type.equalsIgnoreCase("name")){
			we=wd.findElement(By.name(locator));
		}else if(type.equalsIgnoreCase("id")){
			we=wd.findElement(By.id(locator));
		}else if(type.equalsIgnoreCase("xpath")){
			we=wd.findElement(By.xpath(locator));
		}else if(type.equalsIgnoreCase("className")){
			we=wd.findElement(By.className(locator));
		}else if(type.equalsIgnoreCase("cssSelector")){
			we=wd.findElement(By.cssSelector(locator));
		}else if(type.equalsIgnoreCase("tagName")){
			we=wd.findElement(By.tagName(locator));
		}else{
			System.out.println("出错啦，请确认元素查找类型是否正确~~~~");
		}
		return we;		
	}
	
	
	/**
	 * 获取 By对象 
	 * @param locator
	 * @param type
	 * @return By
	 */
	public By getBy(String type,String locator) {
		if (type == null) {
			return By.xpath(locator);
		} else {
			type = type.toLowerCase();
			if (type.equals("xpath")) {
				return By.xpath(locator);
			} else if (type.equals("id")) {
				return By.id(locator);
			} else if (type.equals("linktext") || type.equals("link")) {
				return By.linkText(locator);
			} else if (type.equals("partiallinktext")) {
				return By.partialLinkText(locator);
			} else if (type.equals("name")) {
				return By.name(locator);
			} else if (type.equals("tagname")) {
				return By.tagName(locator);
			} else if (type.equals("classname")) {
				return By.className(locator);
			} else if (type.equals("cssselector") || type.equals("css")) {
				return By.cssSelector(locator);
			} else {
				return By.xpath(locator);
			}
		}
	}
	
	public String[] find_Element(String elementName){
		
		String filename="homepage.properties";				
		String value=ElementBase.initElement(filename,elementName);		
		String v[]=value.split("#");		
		String type = v[0].trim();
		String locator = v[1].trim();
		System.out.println(type+"============"+locator);
		return v;
					
	}
	
	
	public static String initElement(String filename,String elementName){
		String path="/resources/testdata/pages/";
		String value=null;
		try {
			value=ReadProperties.getProp(path, filename, elementName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	//	System.out.println(value);		
		return value.trim();
				
	}
	
	public static WebElement getElement(String s){
		
		String type=s.split("#")[0].trim();
		String locator=s.split("#")[1].trim();

		
		if (type.equalsIgnoreCase("linkText")){
			we=wd.findElement(By.linkText(locator));
		}else if(type.equalsIgnoreCase("name")){
			we=wd.findElement(By.name(locator));
		}else if(type.equalsIgnoreCase("id")){
			we=wd.findElement(By.id(locator));
		}else if(type.equalsIgnoreCase("xpath")){
			we=wd.findElement(By.xpath(locator));
		}else if(type.equalsIgnoreCase("className")){
			we=wd.findElement(By.className(locator));
		}else if(type.equalsIgnoreCase("cssSelector")){
			we=wd.findElement(By.cssSelector(locator));
		}else if(type.equalsIgnoreCase("tagName")){
			we=wd.findElement(By.tagName(locator));
		}else{
			System.out.println("出错啦，请确认元素查找类型是否正确~~~~");
		}
		return we;		
	}
	

	public static WebElement waitGetElement(String s){
		 type=s.split("#")[0].trim();
		 locator=s.split("#")[1].trim();
		 
		WebDriverWait wait =(new WebDriverWait(wd,10));
		return wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver d){
					
			if (type.equalsIgnoreCase("linkText")){
				we=wd.findElement(By.linkText(locator));
			}else if(type.equalsIgnoreCase("name")){
				we=wd.findElement(By.name(locator));
			}else if(type.equalsIgnoreCase("id")){
				we=wd.findElement(By.id(locator));
			}else if(type.equalsIgnoreCase("xpath")){
				we=wd.findElement(By.xpath(locator));
			}else if(type.equalsIgnoreCase("className")){
				we=wd.findElement(By.className(locator));
			}else if(type.equalsIgnoreCase("cssSelector")){
				we=wd.findElement(By.cssSelector(locator));
			}else if(type.equalsIgnoreCase("tagName")){
				we=wd.findElement(By.tagName(locator));
			}else{
				System.out.println("出错啦，请确认元素查找类型是否正确~~~~");
			}
			return we;				
			}

		});	
	}

	/*
	 * 鼠标键盘事件
	 * 
	 */

	public static void click(){
		Actions action = new Actions(wd);
		action.click();
		log.info("鼠标在当前停留位置单击");
	}
	public static void click(WebElement w){
		Actions action = new Actions(wd);
		action.click(w);
		log.info("鼠标点击元素:"+w.getText());
	}
	public static void contextClick(){
		Actions action = new Actions(wd); 
		action.contextClick();// 鼠标右键在当前停留的位置做单击操作 
		log.info("鼠标右键在当前停留的位置做单击操作");
	}

	public static void contextClick(WebElement w){
		Actions action = new Actions(wd); 		 
		action.contextClick(w);
		log.info("鼠标右键单击元素："+w.getText());
	}
	
	public static void doubleclick(){
		Actions action = new Actions(wd); 
		action.doubleClick();// 鼠标在当前停留的位置做双击操作 
	}
	
	public static void doubleclick(WebElement w){
		Actions action = new Actions(wd); 
		action.doubleClick(w);// 鼠标双击指定的元素
	}
			
}
