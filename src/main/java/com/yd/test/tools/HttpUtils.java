package com.yd.test.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtils {
	private  final CloseableHttpClient httpClient;
	public Header [] headers = null;
	protected CloseableHttpResponse response = null;
	protected  TrustManager trustManager = null;
	protected SSLConnectionSocketFactory socketFactory = null;
	private  PSFClient psf = null;
	public PSFClient.PSFRPCRequestData request = null;
	
	private static class HttpUtilHolder {
	        private static final HttpUtils INSTANCE = new HttpUtils();
	}

	public static HttpUtils getIntance() {
	        return HttpUtilHolder.INSTANCE;
	}

	    private HttpUtils() {
			trustManager = new X509TrustManager() {
				
				@Override
				public X509Certificate[] getAcceptedIssuers() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					// TODO Auto-generated method stub
					
				}
			};
			enableSSL();
			RequestConfig config = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000).
			setExpectContinueEnabled(true).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM,AuthSchemes.DIGEST)).setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
		    Registry<ConnectionSocketFactory> socketFactoryRegistry =RegistryBuilder.<ConnectionSocketFactory>create().register("http",PlainConnectionSocketFactory.INSTANCE).register("https", socketFactory).build();
		    PoolingHttpClientConnectionManager conneManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		    conneManager.setMaxTotal(300);
		    conneManager.setDefaultMaxPerRoute(300);
		    httpClient = HttpClients.custom().setConnectionManager(conneManager).setDefaultRequestConfig(config).build();	
		
		    //测试环境
		    String[] serviceCenter = {"10.0.11.71:5201","10.0.11.72:5201"};
		    //线上环境地址
		    //String[] serviceCenter = {"172.17.0.77:5201","172.17.0.78:5201"};
		    try{
		    	 psf = new PSFClient(serviceCenter);	
		    	 request =  new PSFClient.PSFRPCRequestData();
		    }catch(Exception e){
		    	e.printStackTrace();
		    }
			
	}
	    
	public void enableSSL(){
		try{
			SSLContext context = SSLContext.getInstance("TLS");
			context.init(null, new TrustManager[]{trustManager}, null);
			socketFactory = new SSLConnectionSocketFactory(context, NoopHostnameVerifier.INSTANCE);
		    
		}catch(Exception e){
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
	public String doPSFRequest(String service_type){
		String psfResponse = "";
		try{
			psfResponse = psf.call(service_type, request);	
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return psfResponse;
	}
	
	private Object doExecute(HttpUriRequest request) {
		String rs = null;
		Header [] headers = null;
		headers = request.getAllHeaders();
		try {
		//	System.out.println(request.getURI());
			headers = request.getAllHeaders();
			StringBuffer sb = new StringBuffer("header参数：");
		    for(Header header:headers){
		    	sb.append(header.getName()+"  "+header.getValue()+"  ");
			}
//		    System.out.println(sb.toString());
			response = httpClient.execute(request);

			HttpEntity entityStr = response.getEntity();
			rs = EntityUtils.toString(entityStr,"UTF-8").trim();
			EntityUtils.consume(entityStr);

		} catch (Exception e) {
           rs = e.getMessage();
		}		
		return rs;
	}
	
	//get下载文件时使用
		private Object doExecute(HttpUriRequest request,String filePath) {
			String rs = null;
			InputStream in=null;
			OutputStream out=null;
			try {
				response = httpClient.execute(request);
				headers = response.getAllHeaders();
				HttpEntity entityStr = response.getEntity();
				in=entityStr.getContent();
				long length=entityStr.getContentLength();
				if(length<=0){
					System.out.println("下载文件不存在！");
					return null;
				}
				File file=new File(filePath);
				if(!file.exists()){
					file.createNewFile();
				}
				out=new FileOutputStream(file);
				
				byte[] buffer=new byte[4096];
				int readLength=0;
				while ((readLength=in.read(buffer))>0){
					byte[] bytes=new byte[readLength];
					System.arraycopy(buffer, 0, bytes, 0, readLength);
					out.write(bytes);
				}
				out.flush();
				
				rs = EntityUtils.toString(entityStr, "UTF-8").trim();
				EntityUtils.consume(entityStr);
			} catch (Exception e) {
				rs = e.getMessage();
			}finally{
		         try {
		             if(in != null){
		                  in.close();
		               }
		             } catch (IOException e) {
		                e.printStackTrace();
		             }
		             
		            try {
		                 if(out != null){
		                     out.close();
		                 }
		            } catch (IOException e) {
		                 e.printStackTrace();
		             }
		         }
			return rs;
		}
	
	//HttpSendGet方法
	
	public Object doSendGetHeader(String url,Map<String,String>headerParams) {	
		url = url.replaceAll(" +", "%20").replaceAll("\"+", "%22").replaceAll("\\{", "%7b").replaceAll("\\}", "%7d");
		 HttpGet httpGet = new HttpGet(url);	
		for(Map.Entry<String, String> entry:headerParams.entrySet()){
			httpGet.addHeader(entry.getKey(),entry.getValue());
		}		
		return doExecute(httpGet);		 
	}

	
    public Object doSendGet(String url) {	
		url = url.replaceAll(" +", "%20").replaceAll("\"+", "%22").replaceAll("\\{", "%7b").replaceAll("\\}", "%7d");
		 HttpGet httpGet = new HttpGet(url);	
	
		return doExecute(httpGet);		 
	}

	public Object doSendGet(String url,Map<String,String>mapParams) {	
		url = url.replaceAll(" +", "%20").replaceAll("\"+", "%22").replaceAll("\\{", "%7b").replaceAll("\\}", "%7d");
		url=url+"?"+getPostParameter(mapParams);
	//	System.out.println("postParams:"+url);
		 HttpGet httpGet = new HttpGet(url);
		return doExecute(httpGet);		 
	}
    
	public Object doSendGet(String url,Map<String,String>mapParams,Map<String,String>headerParams) {	
		url = url.replaceAll(" +", "%20").replaceAll("\"+", "%22").replaceAll("\\{", "%7b").replaceAll("\\}", "%7d");
		url=url+"?"+getPostParameter(mapParams);
	//	System.out.println("get请求参数："+url);
		 HttpGet httpGet = new HttpGet(url);	
		for(Map.Entry<String, String> entry:headerParams.entrySet()){
			httpGet.addHeader(entry.getKey(),entry.getValue());

		}
		return doExecute(httpGet);		 
	}
	
	public Object doSendGet(String url, String filePath,Map<String, String> mapParams, Map<String, String> headerParams) {
		url = url.replaceAll(" +", "%20").replaceAll("\"+", "%22").replaceAll("\\{", "%7b").replaceAll("\\}", "%7d");
		url = url + "?" + getPostParameter(mapParams);
		HttpGet httpGet = new HttpGet(url);
		for (Map.Entry<String, String> entry : headerParams.entrySet()) {
			httpGet.addHeader(entry.getKey(), entry.getValue());
		}
		return doExecute(httpGet,filePath);
	}
    
    //HttpSendPost方法
    
	public Object doSendPost(String url,String json){
		HttpPost httpPost = new HttpPost(url);
		try{
			httpPost.setHeader("RecContentType", "application/json");
			httpPost.setEntity(new StringEntity(json));
			return doExecute(httpPost);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;	
	}

	public Object doSendPost(String url,Map<String,String> mapParams){
		List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
		if(mapParams==null){
			return null;
		}
		for(Map.Entry<String, String> entry:mapParams.entrySet()){
		 BasicNameValuePair kvPair = new BasicNameValuePair(entry.getKey(), entry.getValue());
		 list.add(kvPair);
		}
		url = url.replaceAll(" +", "%20").replaceAll("\"+", "%22");
			HttpPost httpPost = new HttpPost(url);			
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(list, "utf-8"));
			
			return doExecute(httpPost);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	 public Object doSendPost(String url, Map<String,String>mapParams,Map<String,String> headerParams) {
	    	List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
			if(mapParams!=null){
				for(Map.Entry<String, String> entry:mapParams.entrySet()){
					 BasicNameValuePair kvPair = new BasicNameValuePair(entry.getKey(), entry.getValue());
					 list.add(kvPair);
					}
			//	System.out.println("postParams:"+getPostParameter(mapParams));
			//	System.out.println("postParams2:"+url+"?"+getPostParameter(mapParams));
			}
			HttpPost httpPost = new HttpPost(strParseToUri(url));
			
			for(Entry<String,String> e:headerParams.entrySet()){
				httpPost.addHeader(e.getKey(),e.getValue());
			}
			
			try {
				httpPost.setEntity(new UrlEncodedFormEntity(list, "utf-8"));
				return doExecute(httpPost);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	    }
	 
	    public Object doSendPost(String url, List<BasicNameValuePair> list) {
	        url = url.replaceAll(" +", "%20").replaceAll("\"+", "%22");
	        HttpPost  httpPost = new HttpPost(url);
	        try {
	            httpPost.setEntity(new UrlEncodedFormEntity(list, "utf-8"));
	            return doExecute(httpPost);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

		//upload file
		public Object doSendPost(String url, String filePath, Map<String, String> mapParams,Map<String, String> headerParams) {
			HttpPost httpPost = new HttpPost(strParseToUri(url));
			HttpEntity mEntity;		
			 
			for (Entry<String, String> e : headerParams.entrySet()) {
				httpPost.addHeader(e.getKey(), e.getValue());	
				System.out.println("header===="+e.getKey()+":"+e.getValue());
			}
			
			MultipartEntityBuilder meb = MultipartEntityBuilder.create();
		//	meb.addBinaryBody("photo", new File(filePath), ContentType.create("multipart/form-data"), "1.jpg");		
			meb.addPart("photo",new FileBody(new File(filePath)));
			
			if (mapParams != null) {
				//addPart
//				for (Map.Entry<String, String> entry : mapParams.entrySet()) {
//					meb.addPart(entry.getKey(), new StringBody(entry.getValue() + "", ContentType.MULTIPART_FORM_DATA));
//				}
				
				//addTextBody
				for (Map.Entry<String, String> entry : mapParams.entrySet()) {
					meb.addTextBody(entry.getKey(), entry.getValue(), ContentType.MULTIPART_FORM_DATA);
				}
				
			}
			mEntity = meb.build();
			System.out.println("all Entity:"+mEntity.getContentLength());

			try {
				httpPost.setEntity(mEntity);
				return doExecute(httpPost);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}


		public Object doSendPost1(String url,Map<String, String> mapParams,Map<String, String> headerParams) {
			HttpPost httpPost = new HttpPost(strParseToUri(url));
			HttpEntity mEntity;		
			 
			for (Entry<String, String> e : headerParams.entrySet()) {
				httpPost.addHeader(e.getKey(), e.getValue());
			}
			MultipartEntityBuilder meb = MultipartEntityBuilder.create();
			
			if (mapParams != null) {
				//addPart
//				for (Map.Entry<String, String> entry : mapParams.entrySet()) {
//					meb.addPart(entry.getKey(), new StringBody(entry.getValue() + "", ContentType.MULTIPART_FORM_DATA));
//				}
				
				//addTextBody 
				for (Map.Entry<String, String> entry : mapParams.entrySet()) {
					meb.addTextBody(entry.getKey(), entry.getValue(), ContentType.MULTIPART_FORM_DATA);
				}	
			}
			mEntity = meb.build();
			try {
				httpPost.setEntity(mEntity);
				return doExecute(httpPost);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	    public Object doSendPostXml(String url, Map<String, String> mapParams) {
	        StringBuffer sb = new StringBuffer("<xml>\n");
	        for (Map.Entry<String, String> entry : mapParams.entrySet()) {
	            sb.append("<" + entry.getKey() + ">" + entry.getValue() + "</" + entry.getKey() + ">\n");
	        }
	        sb.append("</xml>");
	        try {
	            url = url.replaceAll(" +", "%20").replaceAll("\"+", "%22");
	            HttpPost httpPost = new HttpPost(url);
	            httpPost.setHeader("Content-Type", "application/xml");
	            httpPost.setEntity(new StringEntity(sb.toString().trim(), "utf-8"));
	            return doExecute(httpPost);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	 
		 
	//拼接Authorization字符串
    
		public String getAuthorizationString(String filename) {
			Map<String,String> AuthorMap=new HashMap<String,String>();
			AuthorMap=HttpUtils.getParameterFromFileAsMap(filename);
			StringBuffer sb = new StringBuffer();
			if(AuthorMap!=null){
				
				AuthorMap.put("oauth_timestamp", String.valueOf(System.currentTimeMillis() / 1000));
				AuthorMap.put("oauth_nonce", String.valueOf(System.currentTimeMillis() / 1000+1000));
				
				for(Map.Entry<String, String> entry:AuthorMap.entrySet()){
					sb.append(entry.getKey()+"="+entry.getValue()+",");				
				}
			}
			
			String AuthorString = sb.substring(0, sb.length()-1);
		//	System.out.println(AuthorString);
			return AuthorString;
			
		}
		
		//拼接post、get参数字符串
		public static String getPostParameter(Map<String,String>map){
			StringBuffer sb = new StringBuffer();
			for(Map.Entry<String, String> entry:map.entrySet()){
				sb.append(entry.getKey()+"="+entry.getValue()+"&");
			}
			return sb.substring(0,sb.length()-1);
		}
			

		public static Map<String,String> getParameterFromFileAsMap(String fileName){
			String filePath = System.getProperty("user.dir")+"/dataFiles/"+fileName;
			//	String filePath = System.getProperty("user.dir")+"/"+fileName;
			File file = new File(filePath);
			Map<String,String> paramsMap = new HashMap<String,String>();
			BufferedReader breader =null;
			InputStreamReader reader = null;
			try{
				reader = new InputStreamReader(new FileInputStream(file), "UTF-8");
				breader = new BufferedReader(reader);		
				String line = "";
				while((line=breader.readLine())!=null){
				String [] params = line.split("#");
				if(params.length==2){			
					paramsMap.put(params[0].trim(), params[1].trim());
				}else{
					paramsMap.put(params[0].trim(), "");	
				}
			}
				breader.close();
				reader.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			return paramsMap;	
		}	

		
		
    
}
