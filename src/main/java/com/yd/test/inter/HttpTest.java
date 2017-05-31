package com.yd.test.inter;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpTest {

	public void getTest(){
		CloseableHttpClient client = null;
		client = HttpClients.createDefault();  
		HttpUriRequest request;
		CloseableHttpResponse response = null;
		String url="https://www.baidu.com";
		request=new HttpGet(url);
		
		try {
			response=client.execute(request);
			System.out.println("response:"+response.toString());
			Header [] head=response.getAllHeaders();
			for(Header s:head){
				System.out.println("get header:"+s.toString());
			}
			
			HttpEntity entityStr=response.getEntity();
			String rs=EntityUtils.toString(entityStr,"UTF-8").trim();
			System.out.println("response entityStr :"+rs);
			EntityUtils.consume(entityStr);
			System.out.println("statusline:"+response.getStatusLine().getStatusCode());
		
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public URI strParseToUri(String url){
		try{
			URL toURL = new URL(url); 
			URI uri = new URI(toURL.getProtocol(), toURL.getHost(), toURL.getPath(), toURL.getQuery(), null); 
			return uri;	
		}catch(Exception e){
			
		}
		return null;
	}
	
	public static void main(String[] args) {
		HttpTest h1=new HttpTest();
		h1.getTest();
//		URI uri=h1.strParseToUri("http://auth.lan.yongche.com/Oauth2/setAccessToken");
//		System.out.println("authority "+uri.getAuthority());
//		System.out.println("scheme "+uri.getScheme());
//		System.out.println("query "+uri.getQuery());
//		System.out.println("path "+uri.getPath());
//		System.out.println("fragment "+uri.getFragment());
//		System.out.println("host "+uri.getHost());
//		System.out.println("port "+uri.getPort());
//		System.out.println("userinfo "+uri.getUserInfo());
		
		
	}
}
