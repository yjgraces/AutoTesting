package com.yd.test.base;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.log4testng.Logger;

import com.yd.test.tools.ReadYaml;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

/**
 * 创建基于Android和iOS的driver
 * 
 *
 */
public class AppDriverFactory {

    private static AndroidDriver<?> androidDriver = null;
    private static IOSDriver<?> iosDriver = null;
    private static Logger log = Logger.getLogger(AppDriverFactory.class);
    @SuppressWarnings("rawtypes")
    public static AndroidDriver<?> createAndroidDriver(String testDeviceName) {
    	Map<?,?> androidDeviceMap=new HashMap<>();
    	androidDeviceMap=ReadYaml.readDeviceConfig(testDeviceName);
        DesiredCapabilities caps = new DesiredCapabilities();
        String appPath=System.getProperty("user.dir")+"\\resources\\testdata\\data\\"+androidDeviceMap.get("appName");        // 不需要安装的话就去掉这个
        File app=new File(appPath);
        caps.setCapability("app", app.getAbsolutePath());
        caps.setCapability("platformName", androidDeviceMap.get("platformName"));
        caps.setCapability("platformVersion", androidDeviceMap.get("platformVersion"));
        caps.setCapability("deviceName", androidDeviceMap.get("deviceName"));
        caps.setCapability("appPackage", androidDeviceMap.get("appPackage"));
        caps.setCapability("appActivity", androidDeviceMap.get("appActivity"));
        caps.setCapability("unicodeKeyboard", androidDeviceMap.get("unicodeKeyboard")); // 支持中文输入
        caps.setCapability("resetKeyboard", androidDeviceMap.get("resetKeyboard")); // 重置输入法为系统默认
        caps.setCapability("noReset", androidDeviceMap.get("noReset"));
        caps.setCapability("noSign", androidDeviceMap.get("noSign"));
        // caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        try {
            androidDriver = new AndroidDriver(new URL("http://127.0.0.1:" + androidDeviceMap.get("port") + "/wd/hub"), caps);
        } catch (Exception e) {
            log.error("Android.appium连接失败");
        }
        return androidDriver;
    }

    @SuppressWarnings("rawtypes")
    public static IOSDriver<?> createIOSDriver(String testDeviceName) {
    	Map<?,?> IOSDeviceMap=new HashMap<>();
    	IOSDeviceMap=ReadYaml.readDeviceConfig(testDeviceName);
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", IOSDeviceMap.get("platformName"));
        caps.setCapability("platformVersion", IOSDeviceMap.get("platformVersion"));
        caps.setCapability("unicodeKeyboard", IOSDeviceMap.get("unicodeKeyboard"));
        caps.setCapability("resetKeyboard", IOSDeviceMap.get("resetKeyboard"));
        caps.setCapability("udid", IOSDeviceMap.get("udid"));

        try {
            iosDriver = new IOSDriver(new URL("http://127.0.0.1:" + IOSDeviceMap.get("port") + "/wd/hub"), caps);
        } catch (MalformedURLException e) {
            log.error("iOS.appium连接失败");
        }

        return iosDriver;
    }

}
