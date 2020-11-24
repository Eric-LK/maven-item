package com.eric.test;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;


import java.util.ArrayList;
import java.util.List;

/**
 * @author liuBing
 */
@Slf4j
public class JSONTest {

    public static void main(String[] args) {

        List<Integer> stringList = new ArrayList<>(){{
            add(123);
            add(456);
        }};


        log.info(JSON.toJSONString(stringList));
        log.info(JSON.toJSON(stringList).toString());
        System.out.println(JSON.toJSONString(stringList));
        System.out.println(JSON.toJSON(stringList));

    }
}
