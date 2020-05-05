package com.eric.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping("hell0")
public class HelloController {

    @ApiOperation("say")
    @GetMapping("hello")
    private String say(){
        return "hello";
    }
}
