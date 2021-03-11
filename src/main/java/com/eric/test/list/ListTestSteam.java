package com.eric.test.list;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuBing
 */
public class ListTestSteam {

    public static void main(String[] args) {
        test01();
    }


    /**
     * map 之后的 filter（过滤） ， distinct（去重）实验
     */
    public static void test01() {
        List<ListEntity> entityList = new ArrayList<>(
                Arrays.asList(
                        new ListEntity(1L, "k1", "v1"),
                        new ListEntity(2L, "k2", "v1"),
                        new ListEntity(3L, "k3", "v2"),
                        new ListEntity(3L, "k3", "")
                )
        );

        List<String> valueList = entityList.stream()
                .map(ListEntity::getValue)
                .filter(value -> !StringUtils.isEmpty(value))
                .distinct()
                .collect(Collectors.toList());

        System.out.println(valueList.toString());
    }
}
