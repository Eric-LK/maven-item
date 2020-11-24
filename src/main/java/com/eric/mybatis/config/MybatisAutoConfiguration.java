package com.eric.mybatis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.eric.mybatis.dao")
public class MybatisAutoConfiguration {
}