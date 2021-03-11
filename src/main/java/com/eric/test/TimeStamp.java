package com.eric.test;


import javax.xml.crypto.Data;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * @author liuBing
 */
public class TimeStamp {

    public static void main(String[] args) {

        LocalDate ld1 = LocalDate.now().minusDays(1);
        LocalDate with = ld1.with(DayOfWeek.MONDAY);

        System.out.println(with);

        LocalDate monday1 =ld1.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
        System.out.println(monday1);

        LocalDate ld = LocalDate.now();
        LocalDate monday =ld.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
        System.out.println(monday);

    }
}
