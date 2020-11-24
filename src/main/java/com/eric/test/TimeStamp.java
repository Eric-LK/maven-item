package com.eric.test;


/**
 * @author liuBing
 */
public class TimeStamp {

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis() - 86400000L * 1);
        //System.out.println(System.currentTimeMillis() + 86400000L * 62);
        System.out.println(System.currentTimeMillis() - 86400000L * 30);

        Long a = 100L;
        Integer b = 2000;

        System.out.println(b - Integer.parseInt(a.toString()));
    }

}
