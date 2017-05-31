package com.yd.test.testcase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jetty.util.IteratingCallback;

public class Te {

	
	public void getToken(){
		
		ArrayList<Object> a1=new ArrayList<>();
		int[] a={1,4,6,3,2,8};
		for(int i:a){
			a1.add(i);
		}

		

		System.out.println(a1);
		
		Iterator it1=a1.iterator();
		while(it1.hasNext()){
			System.out.println(it1.hasNext());
			System.out.println(it1.next());
		}
		
		for(Object x:a1){
			System.out.println("x="+x);
		}
	}
	
	public static void main(String[] args) {
		Te t=new Te();
		t.getToken();
	}
}
