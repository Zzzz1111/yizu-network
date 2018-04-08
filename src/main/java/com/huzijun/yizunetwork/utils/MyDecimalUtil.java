package com.huzijun.yizunetwork.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;

public class MyDecimalUtil {

    private static final Logger logger = LoggerFactory.getLogger(MyTimeUtil.class);

    public static final String TWO_OR_ONE_PLACES = "#.##";

    public static final String TWO_PLACES = "#.00";

    public static final String ALL_INT = "#";

    public static final String TWO_PERCENT = "#.00%";

    public static final String THREE_COMMA = ",###.00";

    public static String formatNumber(String pattern,Object o){
        return new DecimalFormat(pattern).format(o);
    }

    public static int stringToInt(String s){
        return Integer.parseInt(s);
    }

    public static long stringToLong(String s){
        return Long.parseLong(s);
    }

    public static double stringToDouble(String s){
        return Double.parseDouble(s);
    }
}
