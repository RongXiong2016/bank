package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 范正荣
 * @Date 2018/2/16 0016 16:15.
 * 公共工具类
 */
public class CommonUtils {
    // 3325251984 1022 5318
    // 0123456789
    public static Integer idxSexStart = 16;
    public static Integer birthYearSpan = 4;

    public static String getSex(String certificateNo){
        String idxSexStr = certificateNo.substring(idxSexStart, idxSexStart + 1);
        int idxSex = Integer.parseInt(idxSexStr) % 2;
        return (idxSex == 1) ? "M" : "F";
    }

    public static Integer getAge(String certificateNo){
        String year = (birthYearSpan == 2 ? "19" : "") + certificateNo.substring(6, 6 + birthYearSpan);
        String month = certificateNo.substring(6 + birthYearSpan, 6 + birthYearSpan + 2);
        String day = certificateNo.substring(8 + birthYearSpan, 8 + birthYearSpan + 2);
        String birthday = year + '-' + month + '-' + day;


        //年龄
        Calendar certificateCal = Calendar.getInstance();
        Calendar currentTimeCal = Calendar.getInstance();
        certificateCal.set(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day));
        int yearAge = (currentTimeCal.get(currentTimeCal.YEAR)) - (certificateCal.get(certificateCal.YEAR));
        certificateCal.set(currentTimeCal.get(Calendar.YEAR), Integer.parseInt(month)-1, Integer.parseInt(day));
        int monthFloor = (currentTimeCal.before(certificateCal) ? 1 : 0);
        return yearAge - monthFloor;
    }



    /*public static void main(String[] args) throws ParseException {
        String certificateNo = "332525199510125318";
        System.out.println(getSex(certificateNo));
        System.out.println(getAge(certificateNo));
    }*/
}
