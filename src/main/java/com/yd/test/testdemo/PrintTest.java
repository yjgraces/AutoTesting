package com.yd.test.testdemo;

public class PrintTest {

	public static void main(String[] args) {
		Print p=new Print();
		Teacher t1=new Teacher(p, "alibaba", 10, 11, 12);
		Teacher t2=new Teacher(p, "tenxun", 20, 21, 22);
		Teacher t3=new Teacher(p, "jingdong", 30, 31, 32);
		
		Thread td1=new Thread(t1);
		Thread td2=new Thread(t2);
		Thread td3=new Thread(t3);
		
		td1.start();
		td2.start();
		td3.start();
	}

	
	
}
