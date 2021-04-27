package com.eric.test.list;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author liuBing
 */
public class MapTest {

    public static void main(String[] args) {
        Map<String, Function<String, String>> map = new HashMap<>();

        map.put("get", x -> "123" + x);

        Function<String, Integer> function = x -> Integer.parseInt( x + "23");

        System.out.print(function.apply("dee").toString());

    }
}
