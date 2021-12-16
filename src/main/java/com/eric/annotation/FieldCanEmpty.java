package com.eric.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description： 参数字段可以为空的注解
 * @Author: liuBing
 * @DateTime: 2021/11/19 11:30
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldCanEmpty {
}
