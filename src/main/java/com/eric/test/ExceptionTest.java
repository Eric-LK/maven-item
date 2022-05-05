package com.eric.test;

import java.util.IllegalFormatPrecisionException;

/**
 * @author liuBing
 */
public class ExceptionTest {

    public Integer getValue( Integer a,Integer c) {
        if (a.equals(c)){
            throw new IllegalFormatPrecisionException(1);
        }
        return a / c;
    }
}
