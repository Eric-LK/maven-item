package com.eric.utils;

/**
 * @description：
 * @Author: liuBing
 * @DateTime: 2022/5/5 16:28
 */
public class StringUtil {
    /*
        %s --> String.format
        StringBuilder, StringBuffer

     */


    /**
     * 只允许用户输入数字1-9,字母a-z,A-Z,只能半角,不能有空格的正则表达式
     */
    private static final String ONLY_LETTER_OR_NUMBER = "^[a-z0-9A-Z]+$";

    /**
     * 对用户输入的String做校验只允许有数字和大小写字母
     * 不允许全角,只允许半角
     *
     * str.matches方法,底层用的还是如下
     * Pattern.matches(regex, this)
     */
    public static boolean isOnlyLetterOrNumber(String str) {
        return str.matches(ONLY_LETTER_OR_NUMBER);
    }

}
