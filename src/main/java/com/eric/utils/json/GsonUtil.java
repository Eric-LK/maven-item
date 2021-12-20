package com.eric.utils.json;

import com.google.gson.Gson;

/**
 * @description：
 * @Author: liuBing
 * @DateTime: 2021/12/17 16:43
 */
public class GsonUtil {

    Gson gson = new Gson();

    /*
     * // 转对象
     * Person person = gson.fromJson(str, Person.class);
     *
     * // 转list
     * 1、List<Person> ps = gson.fromJson(str, new TypeToken<List<Person>>(){}.getType());
     * 2、Person[] personArray = gson.fromJson(json,Person[].class);
     * List<Person> personList  = Arrays.asList(personArray);
     */
}
