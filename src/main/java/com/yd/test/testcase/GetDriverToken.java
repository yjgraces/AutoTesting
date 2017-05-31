package com.yd.test.testcase;

import java.util.HashMap;
import java.util.Map;

import com.yd.test.HttpUtils.HttpUtils;
import com.yd.test.HttpUtils.InitYamlFile;
import com.yd.test.HttpUtils.TestData;

public class GetDriverToken {

	
	public static String ContentType="application/x-www-form-urlencoded";
    public static String oauth_timestamp = String.valueOf(System.currentTimeMillis() / 1000);
    public String oauth_nonce = String.valueOf(Long.parseLong(oauth_timestamp) + 1000);
	private com.alibaba.fastjson.JSONObject jsonObj = null;
	public String os_name="samsung-SM-G900F";
	public String os_version="6.0.1";
//	public String cellphone="16820170321";
//	public String imei="353222063475222";
//	public String cellphone="16830163333";
//	public String imei="353222063473333";
	public String cellphone="16820160309";
	public String imei="353222063470309";
	public String Authorization="OAuth oauth_consumer_key=\"2afdd89f5c6dbdc34542ab04933a091004eba18e2\",oauth_signature_method=\"PLAINTEXT\",x_auth_mode=\"client_auth\",oauth_version=\"1.0\",oauth_signature=\"5sARLGoVkNAPhh5wq1Hl95crWIk\"";
	public String version="259";
    public String client_secret="9a9c7d97429b8737bf604d0a56aee505";
    public String client_id="car_master";
    public String x_auth_mode="client_auth";
    public String device_type="1";
    public String Authorization1=Authorization+",oauth_timestamp="+oauth_timestamp+",oauth_nonce="+oauth_nonce;

	
	
	public String getcode(){

		String url="https://testing.driver-api.yongche.org/V6/CarMaster/CreateAuthCode";	
		Map<String, String> params = new HashMap<>();
		Map<String, String> headerParams = new HashMap<>();
	    headerParams.put("Content-Type", ContentType);
	    headerParams.put("Authorization", Authorization1);

	    
	    params.put("os_name", os_name);
	    params.put("os_version", os_version);
	    params.put("cellphone", cellphone);
	    params.put("imei", imei);
	    params.put("version", version);
		params.put("code_type", "1");
		params.put("area_code", "86");
		params.put("x_auth_mode", x_auth_mode);
		params.put("is_gzip", "1");
		params.put("device_type", device_type);
		params.put("os_name", "samsung-SM-G900F");
		params.put("city", "北京市");
		params.put("channel_source", "");
	    
	    Object rs=HttpUtils.getIntance().doSendPost(url, params, headerParams);
	    System.out.println("获取验证码Response:"+rs.toString());
	    jsonObj=com.alibaba.fastjson.JSONObject.parseObject(rs.toString());
	    String auth_code=jsonObj.getJSONObject("msg").getString("auth_code");
	    
	    System.out.println(auth_code);
		return auth_code;
	}
	
	public Map<String, String> login(){
		GetDriverToken g1=new GetDriverToken();
		Map<String, String> tokenMap = new HashMap<>();

		Map<String, String> params = new HashMap<>();
		Map<String, String> headerParams = new HashMap<>();
	    headerParams.put("Content-Type", ContentType);
	    headerParams.put("Authorization", Authorization1);

	    
		String url="https://testing.driver-api.yongche.org/V6/CarMaster/Login";
	    String password=g1.getcode();
		params.put("code_type", "1");
		params.put("area_code", "86");
		params.put("x_auth_mode", x_auth_mode);
		params.put("is_gzip", "1");
		params.put("device_type", device_type);
		params.put("os_name", "samsung-SM-G900F");
		params.put("city", "北京市");
		params.put("channel_source", "");
		
	    params.put("os_name", os_name);
	    params.put("os_version", os_version);
	    params.put("cellphone", cellphone);
	    params.put("imei", imei);
	    params.put("version", version);
	    params.put("x_auth_username", cellphone);
	    params.put("client_secret", client_secret);
	    params.put("client_id", client_id);
	    params.put("oauth_timestamp", oauth_timestamp);
	    params.put("oauth_nonce", oauth_nonce);
	    params.put("x_auth_password", password);
	    headerParams.put("Content-Type", ContentType);
	    Object rs=HttpUtils.getIntance().doSendPost(url, params, headerParams);
	    System.out.println("司机登录Response:"+rs.toString());
	    jsonObj=com.alibaba.fastjson.JSONObject.parseObject(rs.toString());
        String oauth_token=jsonObj.getJSONObject("msg").getJSONObject("oauth_token").getString("oauth_token");
        String access_token=jsonObj.getJSONObject("msg").getJSONObject("oauth2_token").getString("access_token");
        tokenMap.put("oauth_token", oauth_token);
        tokenMap.put("access_token", access_token);
		return tokenMap;
	}
	
	public void unbind(String oauth_token,String access_token){
		Map<String, String> unbindParams = new HashMap<>();

		Map<String, String> headerParams = new HashMap<>();
	    headerParams.put("Content-Type", ContentType);
	    headerParams.put("Authorization", Authorization1);
	    
		String url="https://testing.driver-api.yongche.org/Driver/Unbind";

		unbindParams.put("imei", imei);
		unbindParams.put("version", version);
		unbindParams.put("x_auth_mode", x_auth_mode);
		unbindParams.put("device_type", device_type);
		unbindParams.put("oauth_timestamp", oauth_timestamp);
		unbindParams.put("oauth_nonce", oauth_nonce);
		unbindParams.put("oauth_token", oauth_token);
		unbindParams.put("access_token", access_token);

		

	    Object rs=HttpUtils.getIntance().doSendPost(url, unbindParams, headerParams);
	    System.out.println("解绑Response:"+rs.toString());
	}
	
	public void uploadPhoto(String oauth_token,String access_token){
		Map<String, String> params = new HashMap<>();
		Map<String, String> headerParams = new HashMap<>();
	    headerParams.put("Authorization", Authorization1);
		String url="https://testing.driver-api.yongche.org/driver/UploadPhoto";
		//String photoPath="/Users/zhangjian/Desktop/tu.jpg";
	//	String photoPath=(System.getProperty("user.dir")+"/src/main/resources/data/test-data/1.jpg").replace("\\", "/");
		String photoPath=(System.getProperty("user.dir")+"/resources/testdata/data/1.jpg").replace("\\", "/");
		System.out.println("photoPath===="+photoPath);
		
		params.put("imei", imei);
		params.put("version", version);
		params.put("x_auth_mode", x_auth_mode);
		params.put("device_type", device_type);
		
		params.put("oauth_timestamp", oauth_timestamp);
		params.put("oauth_nonce", oauth_nonce);
		params.put("oauth_token", oauth_token);
		params.put("access_token", access_token);
		
		params.put("code_type", "1");
		params.put("area_code", "86");
	//	params.put("x_auth_mode", x_auth_mode);
		params.put("is_gzip", "1");
	//	params.put("device_type", device_type);
		params.put("os_name", "samsung-SM-G900F");
		params.put("city", "北京市");
		params.put("channel_source", "");
		Object rs=HttpUtils.getIntance().doSendPost(url,photoPath, params, headerParams);
	    
	    System.out.println("上传照片:"+rs.toString());
	}


	
	public void getPhoto(String oauth_token,String access_token){
		
		Map<String, String> params = new HashMap<>();
		Map<String, String> headerParams = new HashMap<>();
	    headerParams.put("Authorization", Authorization1);
		String url="http://testing.driver-api.yongche.org/driver/GetFile";
		//String photoPath="/Users/zhangjian/Desktop/tu.jpg";
	//	String photoPath=(System.getProperty("user.dir")+"/src/main/resources/data/test-data/3.jpg").replace("\\", "/");  //需要保存图片的地址及文件名
		 String photoPath=(System.getProperty("user.dir")+"/resources/testdata/data/3.jpg").replace("\\", "/");
		params.put("imei", imei);
		params.put("version", version);
		params.put("x_auth_mode", x_auth_mode);
		params.put("device_type", device_type);
		params.put("oauth_timestamp", oauth_timestamp);
		params.put("oauth_nonce", oauth_nonce);
		params.put("oauth_token", oauth_token);
		params.put("access_token", access_token);
		
		params.put("code_type", "1");
		params.put("area_code", "86");
		params.put("x_auth_mode", x_auth_mode);
		params.put("is_gzip", "1");
		params.put("device_type", device_type);
		params.put("os_name", "samsung-SM-G900F");
		params.put("city", "北京市");
		params.put("channel_source", "");
		
		params.put("media_id", "g2_M00_01_3B_CgALF1kj6SaAJB1FAAackdNlZIo205.jpg"); 
		params.put("file_type", "1");
		Object rs=HttpUtils.getIntance().doSendGet(url, photoPath,params, headerParams);
	    System.out.println("Get照片:"+rs.toString());
	}
	
	
}
