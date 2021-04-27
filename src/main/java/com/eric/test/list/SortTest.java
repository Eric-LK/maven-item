package com.eric.test.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuBing
 */
public class SortTest {
    public static void main(String[] args) {
        List<ListEntity> entityList = new ArrayList<>(
                Arrays.asList(
                        new ListEntity(1L, "k1", "v1"),
                        new ListEntity(0L, "k1", "v1"),
                        new ListEntity(0L, "k1", "v1"),
                        new ListEntity(0L, "k1", "v1"),
                        new ListEntity(123, "k2", "v1"),
                        new ListEntity(321, "k3", "v2"),
                        new ListEntity(55555L, "k3", "")
                )
        );

        List<ListEntity> collect = entityList.stream().sorted(Comparator.comparing(ListEntity::getId)).collect(Collectors.toList());

        System.out.println(collect.toString());

        Collections.reverse(collect);

        System.out.println(collect.toString());

    }
}
