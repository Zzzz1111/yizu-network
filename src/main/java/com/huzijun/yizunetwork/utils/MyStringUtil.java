package com.huzijun.yizunetwork.utils;

import org.apache.commons.lang3.StringUtils;

public class MyStringUtil {
    
    public static boolean isNull(String s){
        if (StringUtils.isBlank(s)){
            return true;
        }
        return false;
    }

    public static String trim(String s){
        return StringUtils.trimToEmpty(s);
    }
}
