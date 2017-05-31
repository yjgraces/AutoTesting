package com.yd.test.tools;

import java.util.Iterator;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.log4testng.Logger;


/**
 * testNG执行case 失败后 ，testNG Listener会捕获执行失败
 * 如果要实现失败自动截图，需要重写Listener的onTestFailure方法
 * 
 */
public class TestNGListener extends TestListenerAdapter {

    private static WebDriver driver;

    private Logger log=Logger.getLogger(this.getClass());

    public static void setDriver(WebDriver driver) {
        TestNGListener.driver = driver;
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        log.info("Test Success");
        super.onTestSuccess(tr);
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        log.error("Test Failure");
        super.onTestFailure(tr);

        ScreenShot screenShot = new ScreenShot(driver);
        screenShot.getScreenShot();
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        log.error("Test Skipped");
        super.onTestSkipped(tr);
    }

    @Override
    public void onStart(ITestContext testContext) {
        log.info("Test Start");
        super.onStart(testContext);
    }

    @Override
    public void onFinish(ITestContext testContext) {
//        log.info("Test Finish");
//        super.onFinish(testContext);
    	log.info("Test Finish");

        Iterator<ITestResult> listOfFailedTests = testContext.getFailedTests().getAllResults().iterator();
        while (listOfFailedTests.hasNext()) {
            ITestResult failedTest = listOfFailedTests.next();
            ITestNGMethod method = failedTest.getMethod();
            if (testContext.getFailedTests().getResults(method).size() > 1) {
                listOfFailedTests.remove();
            } else {
                if (testContext.getPassedTests().getResults(method).size() > 0) {
                    listOfFailedTests.remove();
                }
            }
        }
    }

}