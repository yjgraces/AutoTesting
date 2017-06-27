package com.yd.test.testdemo;

public class MyRunnableTest {

	public static void main(String[] args) {
		MyRunnable1 a1=new MyRunnable1();
		MyRunnable2 a2=new MyRunnable2();
		Thread t1=new Thread(a1);
		Thread t2=new Thread(a2);
		
		t1.setPriority(Thread.MAX_PRIORITY);
		t2.setPriority(Thread.MIN_PRIORITY);
		System.out.println("t1的优先级"+t1.getPriority());
		System.out.println("t2的优先级"+t2.getPriority());
		t1.start();
		t2.start();
	}
}
