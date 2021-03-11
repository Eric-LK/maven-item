package com.eric.test.reflex.entity;

import java.lang.annotation.*;

/**
 * 自定义注解
 */
/*
 *  被此注解标注的注解对象的注解类型信息，在生成文档的时候，也会被写入到文档中
 *  默认是不会写入进去的
 */
@Documented
/*
 * RetentionPolicy（枚举）：表示注释在哪里记录
 *     SOURCE：源码级别，编译时就忽略
 *     CLASS：编译时在类文件中记录，但运行时不需要jvm保留（默认）
 *     RUNTIME：不仅在类文件中记录，而且在jvm中保留，因此可以反读
 */
@Retention(RetentionPolicy.RUNTIME)
/*
 * RetentionPolicy（枚举）：表示注解用在哪
 *     TYPE：类，接口，枚举
 *     FIELD：字段声明
 *     METHOD；方法
 *     CONSTRUCTOR：构造函数
 *     LOCAL_VARIABLE：局部变量
 *     ANNOTATION_TYPE：注释类型声明
 *     PACKAGE：包声明
 */
@Target(ElementType.FIELD)
public @interface AutowiredTest {
}
