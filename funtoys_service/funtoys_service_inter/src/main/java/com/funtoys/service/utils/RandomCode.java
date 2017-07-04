package com.funtoys.service.utils;

import java.util.Random;

/**
 * Created by hejun on 2017/7/4.
 */
public class RandomCode {

    /**
     * 生成4位随机码
     *
     * @return code
     */
    public static String registerCode() {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer code = new StringBuffer(4);
        for (int i = 0; i < 4; i++) {
            char ch = str.charAt(new Random().nextInt(str.length()));
            code.append(ch);
        }
        return code.toString();
    }
}
