package com.yd.test.tools;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestNGRetry implements IRetryAnalyzer {
    public Logger log = Logger.getLogger(this.getClass());
    private int retryCount = 0;
    private int maxRetryCount=2;


    public boolean retry(ITestResult result) {
        if (retryCount <= maxRetryCount) {
            String message = "running retry for  '" + result.getName() + "' on class " + 
                                       this.getClass().getName() + " Retrying " + retryCount + " times";
            log.info(message);
            retryCount++;
            return true;
        }
        return false;
    }
}
