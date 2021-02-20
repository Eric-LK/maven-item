package com.eric.test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author liuBing
 */
public class ListTest {


    public static void main(String[] args) {

        List<String > list = new ArrayList<>();
        list.add("1");
        list.add("4");

        if ((list.contains("1") || list.contains("3")) && !list.contains("2")) System.out.println(" 奥利给 ");
        else System.out.println(" 2 ");


    }


    /**
     * 根据某个字段去重
     */
    @Test
    public void test() {
        List<User> lists = new ArrayList<>(
                Arrays.asList(
                        new User("zhaoliu", "女", 27, 168),
                        new User("xiaohong", "女", 28, 163),
                        new User("xiaoming", "男", 29, 178),
                        new User("lisi", "女", 27, 162)
                )
        );
        List<User> disUsers = lists.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(User::getSex))), ArrayList::new)
        );
        System.out.println("根据对象中的某个字段进行去重操作" + disUsers);

    }
    @Data
    @AllArgsConstructor
    static class User{
        private String name;
        private String sex;
        private Integer age;
        private Integer height;
    }
}
