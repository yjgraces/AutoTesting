package com.yd.test.testdemo;

public class Teacher implements Runnable {

	Print p;
	private String name;
	private int eScore,mScore,yScore;
	public Teacher(Print p,String name,int eScore,int mScore,int yScore){
		this.p=p;
		this.name=name;
		this.eScore=eScore;
		this.mScore=mScore;
		this.yScore=yScore;
		
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		 synchronized(p){
			 p.printScore(name, eScore, mScore, yScore);
		 }
		
	}
	
	
	public Print getP() {
		return p;
	}
	public void setP(Print p) {
		this.p = p;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int geteScore() {
		return eScore;
	}
	public void seteScore(int eScore) {
		this.eScore = eScore;
	}
	public int getmScore() {
		return mScore;
	}
	public void setmScore(int mScore) {
		this.mScore = mScore;
	}
	public int getyScore() {
		return yScore;
	}
	public void setyScore(int yScore) {
		this.yScore = yScore;
	}
	
	
}
