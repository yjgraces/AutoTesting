package com.yd.test.tools;

import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileElement;

public interface WebdriverExecutor {

    //点击元素
    public void click(Locator locator);

    //输入文本
    public void type(Locator locator, String values);

    //获取元素文本
    public String getText(Locator locator);

    //获取元素
    public WebElement findElement( Locator locator) ;

    //判断元素是否出现
    public boolean isElementDisplayed(Locator locator);

//    //向左滑动
//    public void swipeToLeft();
//    //向右滑动
//    public void swipeToRight();
//    //向上滑动
//    public void swipeToUp();
//    //向下滑动
//    public void swipeToDown();
//
//    //通过坐标点击
//    public void tapByXY(int x, int y);

}
