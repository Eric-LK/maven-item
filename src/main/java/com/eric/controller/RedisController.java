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


    @PostMapping("/getTest")
    public List<Fileinfo> fileinfoTest(@RequestBody String param){
        NimRecordVideoDto nimRecordVideoDto = gson.fromJson(param,NimRecordVideoDto.class);

        List<Fileinfo> fileinfos = JSONArray.parseArray(nimRecordVideoDto.getFileinfo(),Fileinfo.class);
        return fileinfos;
    }
}
