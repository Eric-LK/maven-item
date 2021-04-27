package com.eric.test.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author liuBing
 */
public class OptionalTest {

    public static void main(String[] args) {

        Map<String, Integer> map = new HashMap<>() {{
            put("123", 1);
            put("321", 2);
        }};

        Integer value = map.computeIfAbsent("123", f -> {
            // Integer z = f + 1;
            return 123;
        });

        System.out.println(value);

        List<Integer> integers = new ArrayList<>();

        Optional.ofNullable(map.get("123")).ifPresent(integer -> {
            integers.add(integer);
            System.out.println(integers);
        });

        Optional.ofNullable(map.get("111")).ifPresent(integer -> {
            integers.add(integer);
            System.out.println(integers);
        });

        String a = getString.apply("get string");

        LambdaInterface lambdaInterface = (x, y) -> x + y + 1;

        System.out.println(lambdaInterface.operation(2, 1));

    }


    static Function<String, String> getString = param -> {
        System.out.println(param);
        return param + "123";
    };

    // Consumer<String>


    interface voidTest {
        int getInt(int b);
    }

    @Test
    public void test01() {
        voidTest test = (int x) -> 1 + x;

        System.out.println(test.getInt(2));
    }
}
