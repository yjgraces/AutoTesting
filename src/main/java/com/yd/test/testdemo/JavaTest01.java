package com.yd.test.testdemo;

public class JavaTest01 {

	public static void main(String[] args) {
		MyThread mt=new MyThread();
		Thread t=new Thread(mt);
		t.start();
		for(int i=0;i<10;i++){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("MainThread"+i);
			
		}
	}
}
