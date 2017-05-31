package com.yd.test.tools;

public class Locator {

	private String address;  //定位地址
    private int waitSec;    //等待时间
    private ByType byType;  //定位方式


    /**
     * 定位类型枚举
     *
     */
     public enum ByType{
            by, xpath, linkText, id, name, className
        }


    public Locator() {}

    /**
     * Locator构造器，默认定位类型By.xpath
     * 
     * @author Charlie.chen
     * @param element
     */
    public Locator(String address) {
        this.address = address;
        this.waitSec = 3;
        this.byType = ByType.xpath;
    }

    public Locator(String address, int waitSec) {
        this.waitSec = waitSec;
        this.address = address;
        this.byType = ByType.xpath;
    }

    public Locator(String address, int waitSec, ByType byType) {
        this.waitSec = waitSec;
        this.address = address;
        this.byType = byType;
    }

    public String getAddress() {
        return address;
    }

    public int getWaitSec() {
        return waitSec;
    }

    public ByType getBy() {
        return byType;
    }

    public void setBy(ByType byType) {
        this.byType = byType;
    }

    public ByType getByType() {
        return byType;
    }

}
