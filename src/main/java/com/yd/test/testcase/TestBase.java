package com.yd.test.testcase;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {

    public String oauth_token="";
    public String access_token="";
    GetDriverToken g1=new GetDriverToken();
    YamlFileTestCase y1=new YamlFileTestCase();
    @BeforeSuite
	public void setUp(){
		
		Map<String, String> tokenMap = new HashMap<>();
		tokenMap=g1.login();
		oauth_token=tokenMap.get("oauth_token");
		access_token=tokenMap.get("access_token");
	}
    
	@AfterSuite
	public void afterTest(){
	
		System.out.println("access_token:"+access_token);
		System.out.println("oauth_token:"+oauth_token);
		g1.unbind(oauth_token, access_token);
	}
}
