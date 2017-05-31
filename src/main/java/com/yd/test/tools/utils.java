package com.yd.test.tools;

import java.util.Random;

import org.openqa.selenium.WebDriver;

public class utils {

	//设置等待时间 单位为秒
	public static void wait(int n){
		try {
			Thread.sleep(n*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @param type 所期望返回的随机数的类型，包括int，nint（负整数），double，ndouble（负double），char，uchar（特殊字符）, china（中文）
	 * @param length 所期望返回的随机数的长度
	 * @return 随机数
	 * @throws Exception
	 */
	public static String getRandomData(String type, int length) {
		String data = "";
		String negativeData = "";
		String charData[] = { "!", "@", "#", "$", "%", "^", "&", "*" };
		if (type.equals("int")) {
			for (int i = 0; i < length - 1; i++) {
				data += (int) (10 * Math.random());
			}
			data = (int) (9 * Math.random() + 1) + data;
		} else if (type.equals("nint")) {
			for (int i = 0; i < length - 1; i++) {
				data += (int) (10 * Math.random());
			}
			data = "-" + (int) (9 * Math.random() + 1) + data;
		} else if (type.equals("double")) {
			for (int i = 0; i < length - 3; i++) {
				data += (int) (10 * Math.random());
			}
			for (int i = 0; i < 2; i++) {
				negativeData += (int) (10 * Math.random());
			}
			data = (int) (9 * Math.random() + 1) + data + "." + negativeData;
		} else if (type.equals("ndouble")) {
			for (int i = 0; i < length - 3; i++) {
				data += (int) (10 * Math.random());
			}
			for (int i = 0; i < 2; i++) {
				negativeData += (int) (10 * Math.random());
			}
			data = "-" + (int) (9 * Math.random() + 1) + data + "."+ negativeData;
		} else if (type.equals("char")) {
			for (int i = 0; i < length; i++) {
				data += String.valueOf((char) ('a' + (int) (Math.random() * 26)));
			}
		} else if (type.equals("uchar")) {
			for (int i = 0; i < length; i++) {
				Random rnd = new Random();
				data += charData[rnd.nextInt(8)];
			}
		}else if (type.equals("china")) {
			for (int i = 0; i < length; i++) {
				data += "中";
			}
		}
		return data;
	}

	
}
