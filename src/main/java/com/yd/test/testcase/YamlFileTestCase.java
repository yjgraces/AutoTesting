package com.yd.test.testcase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.yd.test.HttpUtils.HttpUtils;
import com.yd.test.HttpUtils.InitYamlFile;
import com.yd.test.HttpUtils.TestData;

public class YamlFileTestCase {
	public InitYamlFile dr=new InitYamlFile();
	public String filePath = System.getProperty("user.dir") + "\\resources\\testdata\\pages\\login2.yaml";
	public ArrayList<?> paramsList;
    public String oauth_timestamp = String.valueOf(System.currentTimeMillis() / 1000);
    public String oauth_nonce = String.valueOf(Long.parseLong(oauth_timestamp) + 1000);

	public void uploadPhoto(String oauth_token,String access_token){

		TestData uploadTd=dr.initYamlData(filePath,"uploadPhoto");
		Map<String, String> headerParams=uploadTd.getHeaderParamsMap();
		Map<String, String> params=uploadTd.getParamsMap();
		Map<String, String> fileMap=uploadTd.getFileMap();
		String url=uploadTd.getUrl();
		String photoPath=(System.getProperty("user.dir")+fileMap.get("file1")).replace("\\", "/").trim();
		params.put("oauth_timestamp", oauth_timestamp);
		params.put("oauth_nonce", oauth_nonce);
		params.put("oauth_token", oauth_token);
		params.put("access_token", access_token);
		String Authorization1=headerParams.get("Authorization")+",oauth_timestamp="+oauth_timestamp+",oauth_nonce="+oauth_nonce;
		headerParams.put("Authorization", Authorization1);
		
		Object rs=HttpUtils.getIntance().doSendPost(url,photoPath, params, headerParams);
	    System.out.println("上传照片:"+rs.toString());
	    
	}
	
	public void testMember(String oauth_token,String access_token){

		TestData uploadTd=dr.initYamlData(filePath,"testMember");
		Map<String, String> headerParams=uploadTd.getHeaderParamsMap();
		Map<String, String> params=uploadTd.getParamsMap();
		String url=uploadTd.getUrl();
		
		params.put("oauth_timestamp", oauth_timestamp);
		params.put("oauth_nonce", oauth_nonce);
		params.put("oauth_token", oauth_token);
		params.put("access_token", access_token);
		String Authorization1=headerParams.get("Authorization")+",oauth_timestamp="+oauth_timestamp+",oauth_nonce="+oauth_nonce;
		headerParams.put("Authorization", Authorization1);
		
		
		Object rs=HttpUtils.getIntance().doSendGet(url, params, headerParams);
	    System.out.println("testMember:"+rs.toString());
	}
	

}
