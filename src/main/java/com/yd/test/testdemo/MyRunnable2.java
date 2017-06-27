package com.yd.test.testdemo;

public class MyRunnable2 implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<100;i++){
			System.out.print("*");
		}
	}

}
