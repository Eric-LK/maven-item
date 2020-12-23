package com.eric.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
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


    @Test
    public void test(){
        /*List<String> a = new ArrayList<>(){{
            add("123");
        }};
        List<Integer> stringList = new ArrayList<>(){{
            add(123);
            add(456);
        }};
        a.forEach(x -> stringList.forEach(y ->{
            System.out.println(System.currentTimeMillis());
            System.out.println(y);
            stringList.remove(y);
        }));
*/

        String[] aaa = "".split(",");
        List<String> listS  = Arrays.asList(aaa);
        System.out.println(listS);

    }
}
