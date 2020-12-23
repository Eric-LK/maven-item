package com.eric.controller;

import com.alibaba.fastjson.JSONArray;
import com.eric.test.entity.Fileinfo;
import com.eric.test.entity.NimRecordVideoDto;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    //RedisUtil redisUtil;

    Gson gson = new Gson();
//
//    @ApiOperation("setJsonString")
//    @GetMapping("/setJsonString")
//    public boolean setJsonString() {
//
//        List<Person> personList = new ArrayList<>() {{
//            add(Person.builder().id(1L).name("eric").build());
//            add(Person.builder().id(2L).name("eric").age(12).build());
//            add(Person.builder().id(3L).name("eric").age(12).build());
//        }};
//
//        /*  ---------------- fastJson ----------------*/
//
//        // json type: Person
//        redisUtil.set("personListJsonPerson", personList);
//
//        // json type: JsonObject
//        redisUtil.set("personListJsonObject", JSONArray.parseArray(JSON.toJSONString(personList)));
//
//        // json string
//        redisUtil.set("personListJsonString", JSON.toJSONString(personList));
//
//
//
//
//        /*---------------------- gson ---------------------------*/
//
//        Person person = Person.builder().id(1L).age(12).name("123").build();
//
//        redisUtil.set("gson", gson.toJson(person));
//
//
//        return true;
//    }
//
//    @GetMapping("/getPersonListJsonPerson")
//    public List<Person> getPersonListJsonPerson() {
//        String personListJsonPerson = redisUtil.get("personListJsonPerson").toString();
//        return JSONArray.parseArray(personListJsonPerson,Person.class);
//    }
//
//    @GetMapping("/getPersonListJsonObject")
//    public List<Person> getPersonListJsonObject() {
//        String personListJsonPerson = redisUtil.get("personListJsonObject").toString();
//        return JSONArray.parseArray(personListJsonPerson,Person.class);
//    }
//
//    @GetMapping("/getPersonListJsonString")
//    public List<Person> getPersonListJsonString() {
//        String personListJsonPerson = redisUtil.get("personListJsonString").toString();
//        return JSONArray.parseArray(personListJsonPerson,Person.class);
//    }
//
//    @GetMapping("/getJsonStringToPersonListByGson")
//    public List<Person> getJsonStringToPersonListByGson() {
//
//        Person[] persons = gson.fromJson(redisUtil.get("personListJsonString").toString(), Person[].class);
//
//        System.out.println(Arrays.toString(persons));
//
//        List<Person> personList =
//                gson.fromJson(redisUtil.get("personListJsonString").toString(), new TypeToken<List<Person>>() {
//                }.getType());
//
//        System.out.println(personList);
//
//        return personList;
//    }
//
//
//    @GetMapping("/getPerson")
//    public Person getPerson() {
//        String personJsonString = redisUtil.get("person").toString();
//        return gson.fromJson(personJsonString,Person.class);
//    }
//
//    @GetMapping("/getPersonList")
//    public List<Person> getPersonList(){
//        String personListJsonString =gson.toJson(redisUtil.get("personListJsonPerson"));
//        return JSONArray.parseArray(personListJsonString,Person.class);
//    }

    @PostMapping("/getTest")
    public List<Fileinfo> fileinfoTest(@RequestBody String param){
        NimRecordVideoDto nimRecordVideoDto = gson.fromJson(param,NimRecordVideoDto.class);

        List<Fileinfo> fileinfos = JSONArray.parseArray(nimRecordVideoDto.getFileinfo(),Fileinfo.class);
        return fileinfos;
    }
}
