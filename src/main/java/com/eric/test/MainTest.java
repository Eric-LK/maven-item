package com.eric.test;

import com.eric.test.entity.DataTest;

/**
 * @author liuBing
 */
public class MainTest {
    public static void main(String[] args) {
        DataTest dataTest = new DataTest();
        dataTest.setId("123");
        System.out.println(dataTest.toString());
    }
}
