package com.eric.letcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liuBing
 */
public class FindMinTwoFromList {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(
                0,1,2,3,4,5
        );

        Integer minFirst = list.get(0) > list.get(1) ? list.get(1) : list.get(0);
        Integer minSecond = list.get(0) < list.get(1) ? list.get(1) : list.get(0);
        for (int i = 2; i < list.size(); i++) {
            if (list.get(i) < minSecond) {
                if (list.get(i) < minFirst) {
                    minFirst = list.get(i);
                } else {
                    minSecond = list.get(i);
                }
            }
        }

        System.out.println("minFirst:" + minFirst + " , minSecond:" + minSecond);
    }
}


