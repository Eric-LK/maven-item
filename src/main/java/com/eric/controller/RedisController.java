package com.eric.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.eric.entity.Person;
import com.eric.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuBing
 */
@Api
@RestController
@RequestMapping("redis")
@Slf4j
public class RedisController {

    @Autowired
    RedisUtil redisUtil;

    @ApiOperation("setJsonString")
    @GetMapping("/set-json-string/{jsonString}")
    public boolean setJsonString(@PathVariable String jsonString){

        /*List<Integer> stringList = new ArrayList<>(){{
            add(123);
            add(456);
        }};

        redisUtil.set("jsonString",JSON.toJSONString(stringList));
        redisUtil.set("json",JSON.toJSON(stringList));*/

        List<Person> people = new ArrayList<>(){{
            add(Person.builder().id(1L).name("eric").age(12).build());
            add(Person.builder().id(2L).name("eric").age(12).build());
            add(Person.builder().id(3L).name("eric").age(12).build());
        }};

        redisUtil.set("person",people);

        redisUtil.set("personJsonString",JSON.toJSONString(people));


        // redisUtil.set("personList",JSONArray.parseArray(people));
        redisUtil.set("personList",JSONArray.parseArray(JSON.toJSONString(people)));

        // log.info(JSON.toJSONString(stringList));
        // log.info(JSON.toJSON(stringList).toString());
        return true;
    }

    @GetMapping("/get-json-string")
    public String getJsonString(){
        return redisUtil.get("jsonString").toString();
    }

    @GetMapping("/get-json")
    public String getJson(){
        return redisUtil.get("json").toString();
    }

    @GetMapping("/getPersonList")
    public List<Person> getPersonList(){
        return (List<Person>) redisUtil.get("personList");
       // return JSONArray.parseArray(redisUtil.get("personList").toString(),Person.class);
    }

    @GetMapping("/getPerson")
    public List<Person> getPerson(){

        return (List<Person>) JSON.toJSON(redisUtil.get("person")) ;

       // return JSONArray.parseArray(redisUtil.get("person").toString(),Person.class);
    }
}
