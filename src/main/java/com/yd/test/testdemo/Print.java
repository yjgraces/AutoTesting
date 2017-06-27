package com.yd.test.testdemo;

public class Print {

	public void printScore(String name,int eScore,int mScore,int yScore){

		System.out.println(name+"的英文成绩："+eScore);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(name+"的数学成绩："+mScore);
		System.out.println(name+"的语文成绩："+yScore);
	}
}
