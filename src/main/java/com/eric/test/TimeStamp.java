package com.eric.test;

import org.junit.Test;

/**
 * @author liuBing
 */
public class TimeStamp {

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis() + 86400000L * 31);
    }

}
