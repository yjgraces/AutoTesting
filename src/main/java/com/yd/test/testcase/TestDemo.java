package com.yd.test.testcase;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

public class TestDemo extends TestBase{

    
//	@Test
	public void testing1(){
		System.out.println("Testing");
		System.out.println("access_token:"+access_token);
		System.out.println("oauth_token:"+oauth_token);
		g1.uploadPhoto(oauth_token, access_token);
		
	}
	
//	@Test
	public void testing2(){
		System.out.println("Testing");
		System.out.println("access_token:"+access_token);
		System.out.println("oauth_token:"+oauth_token);
	
		g1.getPhoto(oauth_token, access_token);
	}
	
//	@Test
	public void testYamlFile(){
		y1.uploadPhoto(oauth_token, access_token);
	}
	
	@Test
	public void testMember(){
		y1.testMember(oauth_token, access_token);

	}

}
