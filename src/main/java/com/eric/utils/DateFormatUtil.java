package com.eric.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @description： 时间工具类
 * @Author: liuBing
 * @DateTime: 2021/11/1 16:22
 */
public class DateFormatUtil {
    private DateFormatUtil() {
    }

    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取当前时间字符串
     *
     * @return 当前时间字符串
     */
    public static String getNowTime() {
        return dateTimeFormatter.format(LocalDateTime.now());
    }
}
