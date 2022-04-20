package com.eric.test.time;


import org.junit.Test;

import javax.xml.crypto.Data;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;

/**
 * @author liuBing
 */
public class TimeStamp {

    public static void main(String[] args) {
    }

    @Test
    public void test(){

        LocalDate localDate = LocalDate.now().minusDays(1);
        LocalTime localTime = LocalTime.of(12,0,0);

        LocalDateTime localDateTime = LocalDateTime.of(localDate,localTime);
        System.out.println(localDateTime);
    }





}
