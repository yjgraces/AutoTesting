package com.yd.test.HttpUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.yaml.snakeyaml.Yaml;


public class ReadYamlNew {

	 public void  readYaml(){
		String filePath = (System.getProperty("user.dir") + "\\resources\\testdata\\pages\\login2.yaml").replace("\\", "/").trim();
	        
	        Yaml yaml = new Yaml();
	        try {
	       
	       	TestDataNew testDataNew = new Yaml().loadAs(new FileReader(filePath),TestDataNew.class);
	       	
	  //	    TestDataNew testDataNew=new TestDataNew();    	
	  //      	testDataNew = yaml.loadAs(new FileReader(filePath),TestDataNew.class);
	        	 
	        	System.out.println(testDataNew.getHost());
	        	System.out.println(testDataNew.getTaskList().get(0).getListData());
	        	System.out.println(testDataNew.getPackageName());
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	 }
	 
	 public void   getFiLeInfo1(){
	        
		 String filePath = (System.getProperty("user.dir") + "\\resources\\testdata\\pages\\login2.yaml").replace("\\", "/").trim();
	//	 String filePath = "/Users/yongche/Documents/comm/code/yd订单全流程/踩点/src/main/dto/out/yamldemo/login2.yaml";
	        Yaml yaml = new Yaml();
	        try {
	            TaskPackage taskPackage = new Yaml().loadAs(new FileReader(filePath),TaskPackage.class);
	            taskPackage.getTaskList().get(0);
	            System.out.println(taskPackage);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	 }
	 
	 public static void main(String[] args) {
		 ReadYamlNew r=new ReadYamlNew();
		// r.readYaml();
		 r.getFiLeInfo1();
	}
}