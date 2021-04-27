package com.eric.test.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author liuBing
 */
public class ListNewTest {

    @Test
    public void test01() {
        List<String> stringList = new ArrayList<>() {{
            add("2");
            add("1");
            add("fsd");
            add("第三方");
            add("dsf");
        }};

        Collections.sort(stringList);

        System.out.print(stringList);

        List<String> a = new ArrayList<>(Arrays.asList("123", "123"));
    }


    /**
     * 根据某个字段去重
     */
    @Test
    public void test() {
        List<ListEntity> lists = new ArrayList<>(
                Arrays.asList(
                        new ListEntity(1L, "k1", "v1"),
                        new ListEntity(2L, "k2", "v1"),
                        new ListEntity(3L, "k3", "v2"),
                        new ListEntity(3L, "k3", "")
                )
        );
        List<ListEntity> disUsers = lists.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(ListEntity::getKey))),
                        ArrayList::new
                )
        );

       Boolean flag =  lists.stream().map(ListEntity::getValue).distinct().count() == lists.size();
        System.out.println(flag);

        System.out.println("根据对象中的某个字段进行去重操作" + disUsers);
    }
}
