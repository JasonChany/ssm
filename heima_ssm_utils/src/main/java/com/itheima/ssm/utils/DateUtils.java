package com.itheima.ssm.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Jason
 * @Date: 2019/4/2 12:26
 * @Description:
 */

public class DateUtils {

    public static String date2String(Date date,String patt){
        DateFormat df = new SimpleDateFormat(patt);
        String dateStr = df.format(date);
        return dateStr;
    }

    public static Date string2Date(String dateStr,String patt) throws ParseException {
        DateFormat df = new SimpleDateFormat(patt);
        Date date = df.parse(dateStr);
        return date;
    }
}
