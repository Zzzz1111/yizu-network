package com.huzijun.yizunetwork.utils;

import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class PropertiesUtil {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);


    public static String getValueBykey(String fileName, String key) {
        String str = null;
        if (null == key)
            return null;
        try {
            PropertyResourceBundle res =
                    (PropertyResourceBundle) ResourceBundle.getBundle(fileName);
            str = new String(res.getString(key).getBytes("ISO-8859-1"),"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static void main(String args[])
    {
        System.out.println(PropertiesUtil.getValueBykey("jpushConfig","masterSecret").getClass());
    }

}




