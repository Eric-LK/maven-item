package com.eric.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lk
 */
@Configuration
@MapperScan(basePackages = "com.eric.dao")
public class MybatisAutoConfiguration {
}