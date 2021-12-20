package com.eric.utils;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @description： 时间工具类
 * @Author: liuBing
 * @DateTime: 2021/11/1 16:22
 */
public class DateUtil {
    private DateUtil() {
    }

    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final Long ONE_DAY_MILLION_SECOND = 86400000L;
    public static final Long ONE_MINUTE_SECOND = 60000L;

    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
    static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    /**
     * 获取当前时间字符串
     *
     * @return 当前时间字符串
     */
    public static String getNowTime() {
        return dateTimeFormatter.format(LocalDateTime.now());
    }



    /**
     * 根据时间戳获取日期
     *
     * @param timestamp 时间戳
     * @return 日期
     */
    public static String getDateByTimeStamp(Long timestamp) {
        if (timestamp == null) {
            return "";
        }
        return timestamp > 0L ? dateFormat.format(new Date(timestamp)) : "格式错误";
    }

    /**
     * 根据时间戳获取时间
     *
     * @param timestamp 时间戳
     * @return 时间
     */
    public static String getTimeByTimeStamp(Long timestamp) {
        if (timestamp == null) {
            return "";
        }
        return timestamp > 0L ? timeFormat.format(new Date(timestamp)) : "格式错误";
    }

    public static String getDateTimeByTimeStamp(Long timestamp) {
        if (timestamp == null) {
            return "";
        }
        return timestamp > 0L ? dateTimeFormat.format(new Date(timestamp)) : "格式错误";
    }


    //获得本周一0点时间
    public static long getThisWeekMondayTimeStamp() {
        // 今天
        LocalDate ld = LocalDate.now();
        // 上周一
        LocalDate monday = ld.with(DayOfWeek.MONDAY);
        // 转为时间戳
        return monday.atStartOfDay(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
    }


    /**
     * 获取几天（前后）的开始时间 (00:00:00)
     * 几天前：负数
     * 几天后：正数
     *
     * @param dayNum 天数
     * @return 结束时间时间戳
     */
    public static Long getTodayStartTime(int dayNum) {
        Calendar todayStart = Calendar.getInstance();
        todayStart.add(Calendar.DATE, dayNum);
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime().getTime();
    }

    /**
     * 获取几天（前后）的结束时间 (23:59:59)
     * 几天前：负数
     * 几天后：正数
     *
     * @param dayNum 天数
     * @return 结束时间时间戳
     */
    public static Long getTodayEndTime(int dayNum) {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.add(Calendar.DATE, dayNum);
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime().getTime();
    }


    /**
     * 秒数转换时分秒
     *
     * @param second 秒
     * @return 时分秒
     */
    public static String secondToHourMinutesAndSecond(Integer second) {
        if (second == null) {
            return "--";
        }
        //秒
        if (second < 60) {
            return second + "s";
        }
        //分
        if (second < 3600) {
            return (second / 60) + "m" + second % 60 + "s";
        }
        //时
        if (second < 3600 * 24) {
            return (second / 60 / 60) + "h" + second / 60 % 60 + "m" + second % 60 + "s";
        }
        //天
        return (second / 60 / 60 / 24) + "d" + (second / 60 / 60 % 24) + "h" + (second / 60 % 60) + "m" + (second % 60) + "s";
    }

    public static String secondToHourMinutesAndSecond(Long second) {
        if (second == null){
            return "--";
        }
        return secondToHourMinutesAndSecond(second.intValue());
    }
}
