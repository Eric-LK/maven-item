package com.eric.test;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAdjusters;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author liuBing
 */
public class MyTimerTaskTest {

    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("123");
            }
        }, 10000);
    }

    @Test
    public void test() {
        // 今天
        LocalDate ld = LocalDate.now();
        // 上周一
        LocalDate monday = ld.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
        // 转为时间戳
        System.out.println(monday.atStartOfDay(ZoneOffset.systemDefault()).toInstant().toEpochMilli());
    }
}
