package com.zlq.blog.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 * Create by lanqZhou on 2020.10.22
 */
public class MD5Utils {

    /**
     * MD5加密方法
     * @param str 加密前的字符串
     * @return 加密后的字符串
     */
    public static String code(String str) {

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            byte[] byteDigest = messageDigest.digest();
            int i;
            StringBuffer stringBuffer = new StringBuffer("");
            for (int offset=0;offset<byteDigest.length;offset++){
                i = byteDigest[offset];
                if(i<0){
                    i +=256;
                }
                if(i<16){
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i));
            }
            //32位加密
            return stringBuffer.toString();
            //16位加密
            //return  stringBuffer.toString().subString(8,24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void main(String[]args){
        MD5Utils md5Utils = new MD5Utils();
        System.out.println(md5Utils.code("111111"));
    }
}