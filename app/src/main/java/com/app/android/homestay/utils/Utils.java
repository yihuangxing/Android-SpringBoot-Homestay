package com.app.android.homestay.utils;

import java.util.regex.Pattern;

public class Utils {

    public static boolean isMobile(String mobile) {
        String reg = "^1\\d{10}$";
        boolean matches = Pattern.matches(reg, mobile);
        return matches;
    }

    /**
     * ^ 匹配一行的开头位置
     * (?![0-9]+$) 预测该位置后面不全是数字
     * (?![a-zA-Z]+$) 预测该位置后面不全是字母
     * [0-9A-Za-z] {8,16} 由6-16位数字或这字母组成
     * $ 匹配行结尾位置
     */
    public static boolean isPwd(String pwd) {
        String reg = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
        boolean matches = pwd.matches(reg);
        return matches;
    }
}
