package com.ifly.vvm.files;

import java.security.MessageDigest;

/**
 * @Description: MD5 工具类
 * @Author: linyj
 * @Date: 2018/4/10 18:03
 */
public class MD5Util {


    private static final String[] HEX_DIGITS = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    private static String byteArrayToHexString(byte[] b) {

        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++){
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {

        int n = b;
        if (n < 0){
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return HEX_DIGITS[d1] + HEX_DIGITS[d2];
    }

    public static String md5Encode(String origin) {

        try {
            String signString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            return byteArrayToHexString(md.digest(signString.getBytes("UTF-8")));
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {

        String s = md5Encode("123");
        System.out.println(s);
    }

}
