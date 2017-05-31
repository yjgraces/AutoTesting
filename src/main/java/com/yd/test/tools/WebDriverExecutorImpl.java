package com.yd.test.tools;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

public class WebDriverExecutorImpl implements WebdriverExecutor {

    private  WebDriver driver;
    public Logger log=Logger.getLogger(this.getClass());

    public WebDriverExecutorImpl(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}


	/**
     * 在元素中中输入文本
     * 
     * @param locator
     * @param values
     * @throws Exception
     */
    public void type(Locator locator, String values){
        WebElement e = (WebElement) findElement(locator);
        e.sendKeys(values);
    }


    /**
     * 点击元素
     * 
     * @author Charlie.chen
     * @param locator
     * @throws Exception
     */
    public void click(Locator locator) {
    	WebElement e = (WebElement) findElement(locator);
        e.click();
    }


    /**
     * 获取元素文本信息
     * 
     * @author Charlie.chen
     * @param locator
     */
    public String getText(Locator locator) {
        // TODO Auto-generated method stub
    	WebElement e = (WebElement) findElement(locator);
        String text=e.getText();
        return text;
    }


    /**
     * 获取元素
     * 
     * @author Charlie.chen
     * @param locator
     * @return
     */
    public WebElement findElement(Locator locator) {
    	WebElement e=null;
        switch (locator.getBy()) {
        case xpath:
            e = (WebElement) driver.findElement(By.xpath(locator.getAddress()));
            break;
        case id:
            e = (WebElement) driver.findElement(By.id(locator.getAddress()));
            break;
        case name:
            e = (WebElement) driver.findElement(By.name(locator.getAddress()));
            break;
        case className:
            e = (WebElement) driver.findElement(By.className(locator.getAddress()));
            break;
        case linkText:
            e = (WebElement) driver.findElement(By.linkText(locator.getAddress()));
            break;
        default:
            e = (WebElement) driver.findElement(By.id(locator.getAddress()));
        }
        return e;

    }


//    /**
//     * 向左滑动
//     */
//    public void swipeToLeft() {
//        int x = driver.manage().window().getSize().width;
//        int y = driver.manage().window().getSize().height;
//        try {
//            driver.swipe((x / 8 * 7), (y / 2 * 1), (x / 8 * 1), (y / 2 * 1), 1000);
//        } catch (Exception e) {
//            driver.swipe((x / 8 * 7), (y / 2 * 1), (x / 8 * 1), (y / 2 * 1), 1000);
//        }
//    }
//
//    /**
//     * 向右滑动
//     */
//    public void swipeToRight() {
//        int x = driver.manage().window().getSize().width;
//        int y = driver.manage().window().getSize().height;
//        try {
//            driver.swipe((x / 8 * 1), (y / 2 * 1), (x / 8 * 7), (y / 2 * 1), 1000);
//        } catch (Exception e) {
//            driver.swipe((x / 8 * 1), (y / 2 * 1), (x / 8 * 7), (y / 2 * 1), 1000);
//        }
//    }
//
//    /**
//     * 向上滑动
//     */
//    public void swipeToUp() {
//        int x = driver.manage().window().getSize().width;
//        int y = driver.manage().window().getSize().height;
//        try {
//            driver.swipe((x / 2 * 1), (y / 4 * 3), (x / 2 * 1), (y / 4 * 1), 1500);
//        } catch (Exception e) {
//            driver.swipe((x / 2 * 1), (y / 4 * 3), (x / 2 * 1), (y / 4 * 1), 1500);
//        }
//    }
//
//    /**
//     * 向下滑动
//     */
//    public void swipeToDown() {
//        int x = driver.manage().window().getSize().width;
//        int y = driver.manage().window().getSize().height;
//        try {
//            driver.swipe((x / 2 * 1), (y / 8 * 1), (x / 2 * 1), (y / 8 * 7), 1000);
//        } catch (Exception e) {
//            driver.swipe((x / 2 * 1), (y / 8 * 1), (x / 2 * 1), (y / 8 * 7), 1000);
//        }
//    }
//
//
//
//    /**
//     * 通过坐标点击 
//     */
//    public void tapByXY(int x, int y) {
//
//        TouchAction to = new TouchAction(driver);
//        try {
//            to.tap(x, y).release().perform();
//        } catch (Exception e) {
//            System.out.println("点击失败");
//        }
//    }


    /**
     * 判断元素是否出现
     * 
     * @author Charlie.chen
     * @param locator
     * @param timeOut
     * @return
     * @throws IOException
     */
    public boolean isElementDisplayed( Locator locator) {
        boolean flag = false;
        try {
            findElement(locator).isDisplayed();
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }



}