package com.eric.utils;

import java.util.Random;

/**
 * @description： 随机数工具类
 * @Author: liuBing
 * @DateTime: 2021/12/20 15:53
 */
public class RandomUtil {


    /*
    构造方法：
    public Random()：没有给种子，用的是默认种子，是当前时间的毫秒值
    public Random(long seed)：给出指定的种子


    public int nextInt()：返回的是int范围内的随机数
    public int nextInt(int n)：返回的是[0,n)范围的内随机数

     */

    public static void main(String[] args) {
        // 创建对象
        // Random r = new Random();
        Random r = new Random(1111);

        for (int x = 0; x < 10; x++) {
            // int num = r.nextInt();
            int num = r.nextInt(100) + 1;
            System.out.println(num);
        }
    }
}
