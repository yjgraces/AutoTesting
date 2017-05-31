package com.yd.test.testcase;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.yd.test.base.AppDriverFactory;

import io.appium.java_client.android.AndroidDriver;

public class AndroidDemo {
	private AndroidDriver<?> ad = null;
	@Test
	public void testDemo(){
		ad = AppDriverFactory.createAndroidDriver("T600"); 
		ad.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
}
