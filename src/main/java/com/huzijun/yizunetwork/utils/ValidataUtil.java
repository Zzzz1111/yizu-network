package com.huzijun.yizunetwork.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidataUtil {
      /**
         * 检查手机号是否正确
         * @param mobiles
         * @return
         */
        public static boolean isPhoneNumber(String mobiles){
            Pattern p = Pattern.compile("^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$");
            Matcher m = p.matcher(mobiles);
            return m.matches();
        }

        /**
         * 检查密码是否有效
         * @param pwd
         * @return
         */
        public static boolean isValidPwd(String pwd){
            Pattern p = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$");
            Matcher m = p.matcher(pwd);
            return m.matches();
        }
}
