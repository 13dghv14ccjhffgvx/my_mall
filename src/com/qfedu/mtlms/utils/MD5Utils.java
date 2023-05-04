package com.qfedu.mtlms.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description 对字符串进行MD5编码
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class MD5Utils {

    public static String md5Encode(String pwd){
        String str = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(pwd.getBytes());
            byte[] bs = md5.digest();  //加密
            str = new BigInteger(1, bs).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return str;
    }

}
