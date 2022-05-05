package com.eric.utils.number;

import java.text.DecimalFormat;

/**
 * @description： 随机数工具类
 * @Author: liuBing
 * @DateTime: 2021/12/20 15:53
 */
public class NumberUtil {






    /**
     * 获取百分比(保留两位小数)
     *
     * @param molecule    分子
     * @param denominator 分母
     * @return 百分比
     */
    public static String getPercentage(Double molecule, Double denominator) {
        return new DecimalFormat("#0.00").format(molecule / denominator) + "%";
    }

    public static String getPercentageByFour(Double number) {
        return new DecimalFormat("#0.0000").format(number * 100) + "%";
    }

    public static String getPercentageByFourNoPercentSign(Double number) {
        return new DecimalFormat("#0.0000").format(number * 100) ;
    }
}
