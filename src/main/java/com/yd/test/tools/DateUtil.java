package com.yd.test.tools;


import bsh.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
	
	/**
	 * get Now() with non split
	 * @return format: yyyyMMddHHmmss
	 */
	public static String getNowWithNonSplit() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String result = formatter.format(date);
		return result;
	}
	
	/**
	 * get Now() with default format
	 * @return format: yyyy-MM-dd HH:mm:ss
	 */
	public static String getNowWithDefault() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String result = formatter.format(date);
		return result;
	}
	
	public static String getNowWithFomat(String fomat) {
		SimpleDateFormat formatter = new SimpleDateFormat(fomat);
		Date date = new Date();
		String result = formatter.format(date);
		return result;
	}
	
	public static Date getYesterdayDate(long millis) {
        Date nextDay = new Date(millis);
        Calendar calendar = Calendar.getInstance();
        
        calendar.setTime(nextDay);
        calendar.add(Calendar.DATE, -1);
        nextDay = (Date) calendar.getTime();
        return nextDay;
	}
	
	
	public static Date getLastYesterdayDate(long millis) {
        Date nextDay = new Date(millis);
        Calendar calendar = Calendar.getInstance();        
        calendar.setTime(nextDay);
        calendar.add(Calendar.DATE, -2);
        nextDay = (Date) calendar.getTime();
        return nextDay;
	}
	public static Date getNextDate(long millis) {
        Date nextDay = new Date(millis);
        Calendar calendar = Calendar.getInstance();
        
        calendar.setTime(nextDay);
        calendar.add(Calendar.DATE, 1);
        nextDay = (Date) calendar.getTime();
        return nextDay;
	}
	
	public static Date getNextMonth(long month) {
        Date nextDay = new Date(month);
        Calendar calendar = Calendar.getInstance();
        
        calendar.setTime(nextDay);
        calendar.add(Calendar.MONTH, 1);
        nextDay = (Date) calendar.getTime();
        return nextDay;
	}
	
	
	public static Date getNextDateTime(int day, int hour, int min, int second) {
        Date nextDay = new Date(second);
        Calendar calendar = Calendar.getInstance();
        
        calendar.setTime(nextDay);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, second);
        nextDay = (Date) calendar.getTime();
        return nextDay;
	}
	
	public static String getNextDateWithFomat(String fomat,long millis) {
		SimpleDateFormat formatter = new SimpleDateFormat(fomat);
		Date date = getNextDate(millis);
		String result = formatter.format(date);
		return result;
	}
	/**
	 * 获取昨天的日期
	 * @param fomat
	 * @param millis
	 * @return
	 */
	public static String getYesterdayDateWithFomat(String fomat,long millis) {
		SimpleDateFormat formatter = new SimpleDateFormat(fomat);
		Date date = getYesterdayDate(millis);
		String result = formatter.format(date);
		return result;
	}
	/**
	 * 获取前天的日期
	 * @param fomat
	 * @param millis
	 * @return
	 */
	public static String getLastYesterdayDateWithFomat(String fomat,long millis) {
		SimpleDateFormat formatter = new SimpleDateFormat(fomat);
		Date date = getLastYesterdayDate(millis);
		String result = formatter.format(date);
		return result;
	}
	
	public static String getNextMonthWithFomat(String fomat, long month) {
		SimpleDateFormat formatter = new SimpleDateFormat(fomat);
		Date date = getNextMonth(month);
		String result = formatter.format(date);
		return result;
	}
	
	public static Date getDateWithNoTime(String format, String date) throws Exception{
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date result = (Date) formatter.parse(date);
		return result;
	}
	
	public static String getNextDateWithFomat(String fomat, String date, int day) 
	throws ParseException, java.text.ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(fomat);
		Date nextDay = (Date) formatter.parse(date);
		
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nextDay);
        
        calendar.add(Calendar.DATE, day);
        nextDay = (Date) calendar.getTime();
        
        String result = formatter.format(nextDay);
		return result;
	}
	
	/**
	 * 比较日期大小，没有时分秒
	 * @param big
	 * @param small
	 * @return 返回true or false
	 */
	public static boolean compareDate(String big, String small){
		boolean result = false;
		try{
			Date d1 = DateUtil.getDateWithNoTime("yyyy-MM-dd", big);
			Date d2 = DateUtil.getDateWithNoTime("yyyy-MM-dd", small);
			result = d1.compareTo(d2) > 0 ? true : false;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	/**
	 * @author yangweiwei 20151106
	 * 比较日期的大小（含有时分秒）
	 * @param big
	 * @param small
	 * @return 返回true or false
	 */
	public static boolean compareDateTime(String big, String small){
		boolean result = false;
		try{
			Date d1 = DateUtil.getDateWithNoTime("yyyy-MM-dd HH:mm:ss", big);
			Date d2 = DateUtil.getDateWithNoTime("yyyy-MM-dd HH:mm:ss", small);
			result = d1.compareTo(d2) > 0 ? true : false;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	
	/**@author yangweiwei 20151102
	 * 获取本周一到本周六的日期(第一天为周一)
	 * @return 返回本周一到本周日的日期集合
	 */
	public static List<String> getNowWeekdays() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        // 今天是一周中的第几天
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK );
        List<String> list = new ArrayList<String>();
        if (calendar.getFirstDayOfWeek() == Calendar.SUNDAY) {
        	calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        // 计算一周开始的日期
        calendar.add(Calendar.DAY_OF_MONTH, -dayOfWeek);
         
        for (int i=1;i<=7;i++) {
        	calendar.add(Calendar.DAY_OF_MONTH, 1);
            String a=sdf.format(calendar.getTime());
            list.add(a);	
        }
        return list;
	}
	
	/**@author yangweiwei 20151102
	 * 获取下下周一到下下周日的日期
	 * @return 返回下下周一到下下周日的日期集合
	 */
	public static List<String> getNextNextWeekdays() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 List<String> list = new ArrayList<String>();
		 calendar.setFirstDayOfWeek(Calendar.MONDAY);
		 calendar.add(Calendar.WEEK_OF_MONTH,2);	 
		for (int i = 0; i < 7; i++) {								
			calendar.add(Calendar.DATE, -1 * calendar.get(Calendar.DAY_OF_WEEK) + 2 + i);
			String a =dateFormat.format(calendar.getTime());
			list.add(a);								
		}
		return list;						 
	}
	
	/**
	 * @author yangweiwei 20151116
	 * 获取下周一到下周日的日期
	 * @return 返回下周一到下周日的日期
	 */
	public static List<String> getNextWeekdays() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 List<String> list = new ArrayList<String>();
		 calendar.setFirstDayOfWeek(Calendar.MONDAY);
		 calendar.add(Calendar.WEEK_OF_MONTH,1);	 
		for (int i = 0; i < 7; i++) {								
			calendar.add(Calendar.DATE, -1 * calendar.get(Calendar.DAY_OF_WEEK) + 2 + i);
			String a =dateFormat.format(calendar.getTime());
			list.add(a);								
		}
		return list;						 
	}
	
	
	/**
	 * @author yangweiwei 20151106
	 * 获取上周一到上周日的日期
	 * @return 返回上周一到上周日的日期
	 */
	public static List<String> getLastWeekdays() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 List<String> list = new ArrayList<String>();
		 calendar.setFirstDayOfWeek(Calendar.MONDAY);
		 calendar.add(Calendar.WEEK_OF_MONTH,-1);	 
		for (int i = 0; i < 7; i++) {								
			calendar.add(Calendar.DATE, -1 * calendar.get(Calendar.DAY_OF_WEEK) + 2 + i);
			String a =dateFormat.format(calendar.getTime());
			list.add(a);								
		}
		return list;						 
	}
	
    /**  @author yangweiwei 20151102
     * 根据日期字符串判断当月第几周  
     * @param str 输入的日期 
     * @return 返回对应的日期是当月第几周
     * @throws Exception  
     */  
    public static int getWeek(String str) throws Exception{  
//        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");  
//        Date date =sdf.parse(str);  
//        Calendar calendar = Calendar.getInstance();  
//        calendar.setTime(date);  
//        calendar.setFirstDayOfWeek(Calendar.MONDAY);
//        //第几周  
//        int week = calendar.get(Calendar.WEEK_OF_MONTH);
//        if(week>=5){
//        	week=1;
//        }
//        //第几天，从周一开始  
//        //int day = calendar.get(Calendar.DAY_OF_WEEK);  
//        return week;  
        
          Calendar cal = Calendar.getInstance();
          cal.add(Calendar.DATE, 7);
//        int year = cal.get(cal.YEAR);
//        int month =cal.get(cal.MONTH)+1;
        int week =  cal.get(cal.DAY_OF_WEEK_IN_MONTH);
        return week;
    }  
    /**  @author yangweiwei 20151102
     * 根据日期字符串判断某日周几
     * @param str 输入的日期 
     * @return  返回本周日到下周六的星期集合
     * @throws Exception  
     */  
    public static String getWeekday(String str) throws Exception{  
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");  
        Date date =sdf.parse(str);  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);    
        //第几天，从周日开始  
        int day = calendar.get(Calendar.DAY_OF_WEEK);  
        String weekDay;
        if(day==1){
        	weekDay="星期日";        	
        }else if(day==2){
        	weekDay="星期一";
        }else if(day==3){
        	weekDay="星期二";
        }else if(day==4){
        	weekDay="星期三";
        }else if(day==5){
        	weekDay="星期四";
        }else if(day==6){
        	weekDay="星期五";
        }else{
        	weekDay="星期六";
        }
        return weekDay;  
    } 
    /**
     * 获取年月 i为0则获取本月的，1为下月的，依次.....
     * @author yangweiwei
     * @date 2015年12月5日
     * @return 返回年月  xx年xx月
     */
    public static String getYearMoth(int i){
    	 int year;
         int month;
         String date;
         Calendar calendar = Calendar.getInstance();
         year = calendar.get(Calendar.YEAR)+i;
         month = calendar.get(Calendar.MONTH) + 1+i;
         if(month>12){
         	year=calendar.get(Calendar.YEAR)+1;
         	month=i;
         }
//         date = year + "年" + ( month<10 ? "0" + month : month)+"月";
         date = year + "年" + month+"月";
         return date;
    }

	/**
	 * 获取昨天的日期
	 * @return
     */
	public static String getYesterdayDate(){
		Calendar   cal   =   Calendar.getInstance();
		cal.add(Calendar.DATE,   -1);
		String yesterday = new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
		return yesterday;
	}
    
}
