package com.jiang.chat.utils;

/**
 * \* Created with IntelliJ IDEA.
 * \* 作者: jiang
 * \* 日期: 2019/8/3
 * \* 事件: 22:08
 * \* 描述:MD5加密
 * \
 */
import java.security.MessageDigest;
import org.apache.commons.codec.binary.Base64;

public class MD5Utils {

    public static String getMD5Str(String strValue) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        String newstr = Base64.encodeBase64String(md5.digest(strValue.getBytes()));
        return newstr;
    }

    public static void main(String[] args) {
        try {
            String md5 = getMD5Str("imooc");
            System.out.println(md5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
