package com.eric.letcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuBing
 */
public class FindMinTwoFromList {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(123);
        list.add(2);
        list.add(32);
        list.add(43);
        list.add(10);

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


