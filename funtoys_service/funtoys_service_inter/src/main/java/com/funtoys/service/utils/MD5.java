package com.funtoys.service.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by hejun on 2017/7/3.
 */
public class MD5 {
    private static Logger logger = LoggerFactory.getLogger(MD5.class);

    /**
     * 利用MD5进行加密
     */
    public static String Encoder(String value) {
        try {
            //确定计算方法
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            //加密后的字符串
            String encodeValue = base64en.encode(md5.digest(value.getBytes("utf-8")));
            return encodeValue;
        } catch (NoSuchAlgorithmException e) {
            logger.error("MD5加密报错：{}", e.getMessage());
        } catch (UnsupportedEncodingException e) {
            logger.error("MD5加密报错：{}", e.getMessage());
        }
        return null;
    }

}
