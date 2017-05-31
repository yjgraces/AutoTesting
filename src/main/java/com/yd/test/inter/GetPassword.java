package com.yd.test.inter;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;

import com.yd.test.tools.ReadProperties;

public class GetPassword {

	public String getERPLoginPassord(String cellphone){
		Map<String,String> mapParams = new HashMap<>();
		mapParams.put("app_id", "3");
		mapParams.put("cn", "E");
		mapParams.put("done", "https://platform.yongche.com/");
//		mapParams.put("login", "wangbingwei");
//		mapParams.put("password", "Password01!");
		
		mapParams.put("login", "yaojing");
		mapParams.put("password", "Tf58228888");
	     Object rs =HttpUtils.getIntance().doSendPost("https://sso.yongche.com/auth/login", mapParams);
		System.out.println("/auth/login:"+rs.toString());
		Header [] headers = HttpUtils.getIntance().headers;
		Map<String,String> erpheaderParams = new HashMap<>();
		String cookie ="";
		String cookie1="";
		String cookie2="";
		
				
		for(int i=0;i<headers.length;i++){
			if(headers[i].getValue().matches("B=.*")){				
				cookie1=headers[i].getValue().split(";")[0]+";";
			}else if(headers[i].getValue().matches("E_3=.*")){
				cookie2=headers[i].getValue().split(";")[0];
			}
		}
		cookie=cookie1+cookie2;
		erpheaderParams.put("Upgrade-Insecure-Requests", "1");
		System.out.println("cookie:"+cookie);
		erpheaderParams.put("Cookie", cookie);
		rs = HttpUtils.getIntance().doSendGetHeader("https://platform.yongche.com/driver/test?p="+cellphone, erpheaderParams);
		String password = rs.toString();
	
		System.out.println("driver/test:"+rs.toString());
		int start = password.indexOf(":");
		int endindex = password.indexOf("imei");
		System.out.println("start:"+start+",endindex:"+endindex);
		if(endindex==-1){
			password = password.split(":")[1].trim();
		}else{
			password = password.substring(start+1, endindex).trim();
		}
		System.out.println("password:"+password);
	    return password;
	}
	
	
	
	public static String getERPLoginCookies(String cellphone){
		Map<String,String> mapParams = new HashMap<>();
		mapParams.put("app_id", "3");
		mapParams.put("cn", "E");
		mapParams.put("done", "https://platform.yongche.com/");
	//	mapParams.put("done", "https://testing.sso.backend.yongche.org/");		
		mapParams.put("login", "yaojing");
		mapParams.put("password", "Tf58228888");
	     Object rs =HttpUtils.getIntance().doSendPost("https://sso.yongche.com/auth/login", mapParams);
	 //    Object rs =HttpUtils.getIntance().doSendPost("https://testing.sso.backend.yongche.org/auth/login", mapParams);
	     System.out.println("/auth/login:"+rs.toString());
		Header [] headers = HttpUtils.getIntance().headers;

		String cookie3="";
		
		for(int i=0;i<headers.length;i++){
			if(headers[i].getValue().matches("E_1=.*")){
				cookie3=headers[i].getValue().split(";")[0];
			}
		}
		cookie3=cookie3.substring(4);
		try {
			ReadProperties.setProp("test", cookie3, "webdriver.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	("webdriver.properties", "E_1");
		System.out.println("cookie:===  "+cookie3);
		
		return cookie3;
	}
	
	public static void main(String[] args) {
		GetPassword g=new GetPassword();
		//g.getERPLoginPassord("16830163333");
		//g.getERPLoginPassord("16868605018");
		GetPassword.getERPLoginCookies("16830163331");
	}
	
}
