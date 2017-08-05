package com.acoderx.demo.commons.util;

/**
 * Created by xudi on 2017/8/5.
 */
public class StringUtils {
    public static boolean isNotEmpty(String... ss) {
        for (String s : ss) {
            if(s==null||s.length()==0){
                return false;
            }
        }
        return true;
    }

    public static String toString(Object o) {
        if (o != null) {
            return o.toString();
        }
        return "";
    }
}
