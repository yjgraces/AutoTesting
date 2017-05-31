package com.yd.test.tools;

import java.util.HashMap;

import org.apache.log4j.Logger;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumBasePage extends AppiumExecutorImpl {

    public AppiumBasePage(AppiumDriver<?> driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}



	protected AppiumDriver<?> driver;
    protected String pageName;  //页面名称
    protected String xmlPath;   //页面元素路径
    protected HashMap<String, Locator> locatorMap;
//    public Log log = new Log(this.getClass());
    public Logger log = Logger.getLogger(this.getClass());

    public AppiumBasePage(AppiumDriver<?> driver,String pageName) throws Exception  {
        super(driver);
        this.driver = driver;
        this.pageName=pageName;

        //获取资源文件page.xml的路径
        //xmlPath=System.getProperty("user.dir")+"\\src\\main\\java\\com\\dji\\pageObject\\Page.xml";   
        xmlPath=AppiumBasePage.class.getClassLoader().getResource("page.xml").getPath();

        //locatorMap = XmlUtils.readXMLDocument(xmlPath, this.getClass().getSimpleName());
        locatorMap = XmlUtils.readXMLDocument(xmlPath, pageName);

    }



    public void type(String locatorName, String values) {
        super.type(getLocator(locatorName), values);
        log.info("type value is:  " + values);
    }


    public void click(String locatorName) {
        super.click(getLocator(locatorName));
        log.info("click: "+locatorName);
    }


    public String getText(String locatorName) {
        // TODO Auto-generated method stub
        return super.getText(getLocator(locatorName));
    }


    public MobileElement findElement(String locatorName) {
        // TODO Auto-generated method stub
        return super.findElement(getLocator(locatorName));
    }

    public boolean isElementDisplayed(String locatorName) {
        // TODO Auto-generated method stub
        return super.isElementDisplayed(getLocator(locatorName));
    }



    /**
     * 根据locatorName获取Locator
     * 
     * @author Charlie.chen
     * @param locatorName
     * @return
     * @throws IOException
     */
    public  Locator getLocator(String locatorName) {

        Locator locator =  null;

        if(locatorMap!=null)
        {
            locator = locatorMap.get(locatorName);
        }
        return locator;
    }

}