package com.eric.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuBing
 */
public class ListTest {


    public static void main(String[] args) {

        List<Integer> stringList = new ArrayList<>(){{
            add(123);
            add(456);
        }};


        List<Integer> listOne = stringList.stream().filter(x -> x > 200).collect(Collectors.toList());
        System.out.println(listOne);

        List<Integer> listTwo = stringList.stream().filter(x -> x > 2000).collect(Collectors.toList());


        System.out.println(listTwo);
        listTwo.forEach(x -> {
            if (x > 0){
                return;
            }
            System.out.println(x);
        });

    }
}
