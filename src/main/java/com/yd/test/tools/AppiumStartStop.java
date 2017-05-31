package com.yd.test.tools;
import java.io.IOException;
import java.util.concurrent.Executor;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.openqa.selenium.WebElement;
import io.appium.java_client.android.AndroidDriver;

public class AppiumStartStop {
	Process p;
	// Set path of your node.exe file.
	// Progra~1 represents Program Files folder.
	//String nodePath = "/usr/local/bin/node";
	String nodePath ="C://Program Files//nodejs";
	// Set path of your appium.js file.
//	String appiumJSPath = "/Applications/Appium.app/Contents/Resources/node_modules/appium/build/lib/main.js";
	String appiumJSPath="C:/Users/Administrator/AppData/Roaming/npm/node_modules/appium/lib/main.js";
	String cmd = nodePath + " " + appiumJSPath;
	AndroidDriver<WebElement> driver;
	
	 // This method Is responsible for starting appium server.
	public void appiumStart() throws IOException, InterruptedException {
	        // Execute command string to start appium server.
	        p = Runtime.getRuntime().exec(cmd);
	
	        // Provide wait time of 10 mins to start appium server properly.
	        // If face any eÏrror(Could not start a new session...) then Increase
	        // this time to 15 or 20 mins.
	        Thread.sleep(10000);
	        if (p != null) {
	            System.out.println("Appium server Is started now.");
	        }
	    }
	
	    // This method Is responsible for stopping appium server.
	public void appiumStop() throws IOException {
	    System.out.println(p);
	    if (p != null) {
	        p.destroy();
	    }
	    System.out.println("Appium server Is stopped now.");
	}
	
	public void startAppium() throws IOException, InterruptedException {
	    System.out.println("come here");
	    appiumStop();
	    appiumStart();
	}
	

     

}

