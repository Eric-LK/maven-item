package com.eric.utils.number;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

/**
 * @description： 随机数工具类
 * @Author: liuBing
 * @DateTime: 2022/5/5 16:39
 */
public class NumberRandomUtil {

    /*
        构造方法：
            public Random()：没有给种子，用的是默认种子，是当前时间的毫秒值
            public Random(long seed)：给出指定的种子


            public int nextInt()：返回的是int范围内的随机数
            public int nextInt(int n)：返回的是[0,n)范围的内随机数

     */

    private static final Random RANDOM = new Random();


    /**
     * 获取随机字符串（数字 + 字母）
     *
     * @param number 字符串长度
     */
    public static String randomAlphanumeric(Integer number) {
        return RandomStringUtils.randomAlphanumeric(16);
    }


    /**
     * 获取随机int数据
     *
     * @param bound int最大值
     * @return [0, bound)范围的内随机数
     */
    public static int getRandomInt(Integer bound) {
        return RANDOM.nextInt(bound);
    }


}
