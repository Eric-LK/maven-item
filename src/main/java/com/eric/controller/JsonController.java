package com.eric.controller;

import com.eric.dto.Person;
import com.eric.utils.json.FastJsonUtil;
import org.junit.Test;

import java.util.Map;

/**
 * @description：
 * @Author: liuBing
 * @DateTime: 2021/12/17 14:43
 */
public class JsonController {

    @Test
    public void beanToMap(){

        Person person =new Person();
        person.setName("eric");
        person.setId(1L);
        person.setAge(18);

        Map<Object,Object> form = FastJsonUtil.beanToMap(person);
        form.put("name","lk");

        System.out.println(form);
    }
}
