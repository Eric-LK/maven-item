package com.eric.test.number;

import java.text.DecimalFormat;

/**
 * @author liuBing
 */
public class DoubleTest {
    public static void main(String[] args) {

        // 保留两位小数
        Double a = 12.00D;
        System.out.println(new DecimalFormat("#0.00").format(a));
    }
}
