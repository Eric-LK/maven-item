package com.eric.test.number;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liuBing
 */
public class AtomicTest {
    public static void main(String[] args) {

        AtomicInteger a  = new AtomicInteger(0);

        // 先增加后获取
        while(a.get() < 10){
            System.out.println(a.incrementAndGet());
        }

        // 先获取后增加
        while(a.get() < 10){
            System.out.println(a.getAndIncrement());
        }
    }
}
