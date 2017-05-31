package com.yd.test.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.yd.test.tools.ReadProperties;


public class ReadProperties {
	
	public static void main(String[] args) throws FileNotFoundException {
		ReadProperties r=new ReadProperties();
		String filename="homepage.properties";
		
		//读取属性文件

		System.out.println("读取属性文件host:"+ReadProperties.getProp("/resources/testdata/pages/",filename, "help_link"));
		System.out.println("读取属性文件host:"+ReadProperties.getProp("/resources/testdata/pages/",filename, "homepage_link"));
		System.out.println("读取属性文件host:"+ReadProperties.getProp("/resources/testdata/pages/",filename, "partner_link"));
		System.out.println("读取属性文件host:"+ReadProperties.getProp("/resources/testdata/pages/",filename, "jituanyongche_link"));
		
		
		//	System.out.println("读取属性文件chromeDriver:"+r.getProp(filename, "chromeDriver"));
				
		//将properties值写入文件
//		try {
//			r.setProp("host2", "13910147603,15101537885,13911483068", filename);
//			System.out.println("driver-apiphone写入成功");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}		
	}

	
//获取properties文件值的方法,输入文件路径和key,输出value 
	public static String getProp(String filename,String key) throws FileNotFoundException{
		Properties Prop = new Properties();
		try {
			String filepath=System.getProperty("user.dir")+"/resources/config/"+filename;			
			FileInputStream file=new FileInputStream(filepath);		
			Prop.load(file);
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		String rs=Prop.getProperty(key);
		return rs;		
	}

	public static String getProp(String path,String filename,String key) throws FileNotFoundException{
		Properties Prop = new Properties();
		try {
			String filepath=(System.getProperty("user.dir")+path+filename).replace("/", "\\");
		//	System.out.println(filepath);
			 InputStream inputStream = new FileInputStream(filepath);
			 BufferedReader bf = new BufferedReader(new  InputStreamReader(inputStream, "UTF-8"));
			 Prop.load(bf);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		String rs=Prop.getProperty(key).toString();
	//	System.out.println(rs+"获取字符编码："+GetEncoding.getEncoding(rs));
		return rs;		
	}
	
	//设置propertis文件值，并写入文件   输入数据key,value,filepath，直接写入文件，没有返回值
	public static void  setProp(String key,String value,String filename) throws FileNotFoundException{
		Properties props=null;
		String filepath=System.getProperty("user.dir")+"/resources/config/"+filename;
		File file=new File(filepath);
		if(!file.exists()){
			try {
				file.createNewFile();
				System.out.println("creat success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		//先读取属性文件，赋值给props
		try {
			FileInputStream fis=new FileInputStream(filepath);
			props=new Properties();
			props.load(fis);
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//通过props写入key value至pros中
		props.setProperty(key, value);
		//将props属性写入文件
		FileOutputStream fos=new FileOutputStream(filepath);
		try {
			props.store(fos, "update value");
			fos.flush();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	public static void  setProp(String path,String key,String value,String filename) throws FileNotFoundException{
		Properties props=null;
		String filepath=System.getProperty("user.dir")+path+filename;
		File file=new File(filepath);
		if(!file.exists()){
			try {
				file.createNewFile();
				System.out.println("creat success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		//先读取属性文件，赋值给props
		try {
			FileInputStream fis=new FileInputStream(filepath);
			props=new Properties();
			props.load(fis);
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//通过props写入key value至pros中
		props.setProperty(key, value);
		//将props属性写入文件
		FileOutputStream fos=new FileOutputStream(filepath);
		try {
			props.store(fos, "update value");
			fos.flush();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
}
