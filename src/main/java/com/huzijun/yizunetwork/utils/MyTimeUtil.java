package com.huzijun.yizunetwork.utils;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyTimeUtil {

    private static final Logger logger = LoggerFactory.getLogger(MyTimeUtil.class);

    public static final String Y ="yyyy";

    public static final String YM="yyyyMM";

    public static final String Y_M ="yyyy-MM";

    public static final String YMD = "yyyyMMdd";

    public static final String YMDHMS = "yyyyMMdd HH:mm:ss";

    public static final String Y_M_D = "yyyy-MM-dd";

    public static final String Y_M_DHMS = "yyyy-MM-dd HH:mm:ss";

    public static final String YMDHS ="yyyyMMddHHmmss";

    public static final String YMDHMSS="yyyyMMddHHmmssSSS";

    public static final String Y_M_DHMSS="yyyy-MM-dd HH:mm:ss.SSS";

    public static final String Y_M_DHSSSSSSSSSSS="yyyy-MM-dd HH:mm.ss.SSSSSSSSS";

    /**
     * 格式化日期
     * @param date   日期
     * @param pattern  格式
     * @return  日期字符串
     */
    public static String formatDate(LocalDateTime date, String pattern){
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return df.format(date);
    }

    /**
     * 获得当前日期字符串
     * @param pattern  格式
     * @return
     */
    public static String getCurrentDateStr(String pattern){
        return formatDate(LocalDateTime.now(),pattern);
    }


    //2018-04-02 不能转成 LocalDateTime
    /**
     * 把字符串类型的日期转换成LocalDateTime类型
     * @param strDate
     * @param  pattern 转换日期格式
     * @return
     */
    public static LocalDateTime parseLocalDateTime(String strDate, String pattern){
        if(MyStringUtil.isNull(strDate)){
            return null;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime date=  LocalDateTime.parse(strDate,df);
        return date;
    }

    /**
     * 把字符串类型的日期转换成LocalDate类型
     * @param strDate
     * @param  pattern 转换日期格式
     * @return
     */
    public static LocalDate parseLocalDate(String strDate, String pattern){
        if(MyStringUtil.isNull(strDate)){
            return null;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        LocalDate date=  LocalDate.parse(strDate,df);
        return date;
    }

    /**
     * 把字符串类型的日期转换成LocalTime类型
     * @param strDate
     * @param  pattern 转换日期格式
     * @return
     */
    public static LocalTime parseLocalTime(String strDate, String pattern){
        if(MyStringUtil.isNull(strDate)){
            return null;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        LocalTime date=  LocalTime.parse(strDate,df);
        return date;
    }

}
