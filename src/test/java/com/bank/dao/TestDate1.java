package com.bank.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestDate1 {
    public static void main(String[] args) throws ParseException {
        //System.out.println(getDateString(0, 1, 0, 0, 0, 0));
        /*String start_time = getMonthString(3, "yyyy-MM-dd HH:mm:ss", "");
        System.out.println(start_time);
        String now = getStringTime(new Date().getTime()).substring(9);
        System.out.println("now"+now);*/
        /*String start_time = getMonthString(3, "yyyy-MM-dd HH:mm:ss", "");
        System.out.println(start_time);
        System.out.println(start_time.substring(0,10));
        System.out.println(getNowHours());*/
        test1();
    }

    public static String getDateString(int years,int months,int days,int hours,int minutes,int seconds)
    {
        java.util.Date now = new java.util.Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 可以方便地修改日期
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        //修改天数  0表示今天  1表示昨天  -1表示明天
        c.set(Calendar.YEAR, c.get(Calendar.YEAR) - years);
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) - months);
        c.set(Calendar.DATE, c.get(Calendar.DATE) - days);
        c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) - hours);
        c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) - minutes);
        c.set(Calendar.SECOND, c.get(Calendar.SECOND) - seconds);
        return dateFormat.format(c.getTime());
    }

    public static String getDateString(int days,String formate,String time)
    {
        java.util.Date now = new java.util.Date();
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.set(Calendar.DATE, c.get(Calendar.DATE) - days); //修改天数  0表示今天  1表示昨天  -1表示明天
        SimpleDateFormat dateFormat = new SimpleDateFormat(formate);// 可以方便地修改日期 :  "yyyy-MM-dd"

        return dateFormat.format(c.getTime()) + time;//" 00:00:00"
    }
    public static String getMonthString(int months,String formate,String time)
    {
        java.util.Date now = new java.util.Date();
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) - months); //修改天数  0表示当月  1表示上个月  -1表示下个月
        SimpleDateFormat dateFormat = new SimpleDateFormat(formate);// 可以方便地修改日期 :  "yyyy-MM-dd"

        return dateFormat.format(c.getTime()) + time;//" 00:00:00"
    }

    public static String getStringTime(long time){
        java.util.Date now = new java.util.Date(time);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 可以方便地修改日期
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        //修改天数
        c.set(Calendar.YEAR, c.get(Calendar.YEAR));
        c.set(Calendar.MONTH, c.get(Calendar.MONTH));
        c.set(Calendar.DATE, c.get(Calendar.DATE));
        c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE, c.get(Calendar.MINUTE));
        c.set(Calendar.SECOND, c.get(Calendar.SECOND));
        return dateFormat.format(c.getTime());
    }

    public static int getNowHours(){
        java.util.Date now = new java.util.Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 可以方便地修改日期
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        return c.get(Calendar.HOUR_OF_DAY);
    }


    public static void test1() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = formatter.parse("2018-00-00 00:00:00");
        System.out.println(date);
    }
}
