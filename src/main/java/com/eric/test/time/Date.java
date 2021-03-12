package com.eric.test.time;

import org.junit.Test;

import java.util.Calendar;

/**
 * @author liuBing
 */
public class Date {


    /**
     * 获取一天前的0点的时间
     */
    @Test
    public void test01() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, -1); // 几天前

        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        System.out.println(todayStart.getTime().getTime());
    }


    /**
     * 获取一天前的0点的时间
     */
    @Test
    public void test02(){
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.add(Calendar.DATE, -1); // 几天前

        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        System.out.println(todayEnd.getTime().getTime());
    }
}
