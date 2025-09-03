package com.zlq.blog.util;

/**
 * create by lanqingZhou on 2020 11 11
 */
public class StringUtils {

    public static boolean isEmpty(String s){
        if("".equals(s)||s== null){
            return true;
        }
        return false;
    }


    public static boolean isNotEmpty(String s){
        if(!"".equals(s) && s != null){
            return true;
        }
        return false;
    }
}
