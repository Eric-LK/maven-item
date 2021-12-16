package com.eric.exceptions;

/**
 * @description： 自定义异常
 * @Author: liuBing
 * @DateTime: 2021/12/16 14:49
 */
public class MyException extends RuntimeException {
    private int code;

    public MyException(String msg) {
        super(msg);
    }

    public MyException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public static MyException getParamEmptyException(String msg) {
        return new MyException(msg);
    }

    public static MyException getParamErrorException(String msg) {
        return new MyException(msg);
    }

}
