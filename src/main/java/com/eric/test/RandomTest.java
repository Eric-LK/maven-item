package com.eric.test;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Random;
import java.util.TreeSet;

/**
 * @author liuBing
 */
public class RandomTest {

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(RandomStringUtils.randomAlphanumeric(16));
        }
        /*
        for (int i = 0 ; i <5;i++){
            System.out.println(RandomStringUtils.randomPrint(8));
        }

        for (int i = 0 ; i <5;i++){
            System.out.println(RandomStringUtils.randomNumeric(6));
        }*/


        TreeSet<String> treeSet = new TreeSet<>();
        System.out.println(treeSet.size());
        while (treeSet.size() < 20) {
            treeSet.add(RandomStringUtils.randomNumeric(6));
        }
        System.out.println(treeSet.size());
        System.out.println(IdWorker.getId());

        BigDecimal a = BigDecimal.valueOf(2.111);
        System.out.println(a.toString());

    }

    /**
     * 获取多少之内的随机数
     */
    @Test
    public void test01() {
        int i = 0;
        while(i <100){
            System.out.println(new Random().nextInt(10));
            i ++;
        }

        System.out.println(System.currentTimeMillis() - new Random().nextInt(10800000));
    }
}
