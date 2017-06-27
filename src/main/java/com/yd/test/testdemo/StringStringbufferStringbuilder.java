package com.yd.test.testdemo;

public class StringStringbufferStringbuilder {

	/*
	 * 效率：StringBuilder>StringBuffer>String，
	 * 所以，如果这是考虑单线程程序的话，用StringBuilder，
	 * 如果涉及到多线程的，那只能是StringBuffer
	 */
    public static void testString() {
        long start = System.currentTimeMillis();
        String str = null;
        for (int i = 0; i < 20000; i++) {
            str = str + i + ",";
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    //线程安全
    public static void testStringBuffer() {
        long start = System.currentTimeMillis();

        StringBuffer sbuf = new StringBuffer();
        for (int i = 0; i < 20000; i++) {
            sbuf.append(i + ",");
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    //非线程安全，但性能更好
    public static void testStringBulider() {
        long start = System.currentTimeMillis();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 20000; i++) {
            builder.append(i + ",");
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    public static void main(String[] args) {
        testString();
        testStringBuffer();
        testStringBulider();
	}

}
