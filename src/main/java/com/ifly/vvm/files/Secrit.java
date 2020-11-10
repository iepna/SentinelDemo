package com.ifly.vvm.files;

import java.security.MessageDigest;

public class Secrit {
    public static void main(String[] args) {
        String timestamp = System.currentTimeMillis() + "";
        timestamp = timestamp.substring(0,10);
        String signStr = "ekwaj6E3pHLGrj4K" + timestamp + "heliuyan" + "2020100913800090000" + "13711107405" + "MXLD";
        String secrit = MD5Util.md5Encode(signStr).toUpperCase();
        System.out.println(timestamp);
        System.out.println(secrit);
    }
    
}
