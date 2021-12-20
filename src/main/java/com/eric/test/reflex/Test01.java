package com.eric.test.reflex;



import com.eric.test.reflex.entity.Age;
import com.eric.test.reflex.entity.Person;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 通过反射侧面反映ioc的过程
 */
public class Test01 {

    @Test
    public void test() throws Exception {
        Person person = new Person();
        Class<? extends Person> aClass = person.getClass();
        // 创建一个Age对象
        Age age = new Age();
        // 获取 person 对象的 age 属性的信息
        Field ageField = aClass.getDeclaredField("age");
        // 提升权限，保证私有属性也能访问到
        ageField.setAccessible(true);
        // 获取属性的名字
        String name = ageField.getName();
        // 根据属性的名字获取类的名字
        name = name.substring(0,1).toUpperCase() + name.substring(1,name.length());
        // 根据类的名字获取类的set方法名
        String setMethodName = "set" + name;
        // 获取属性对应的set方法对象
        Method method = aClass.getMethod(setMethodName, Age.class);
        // 执行set方法 （参数1：方法所属对象，参数2：方法需要的参数）
        method.invoke(person,age);
        System.out.println(person.getAge());
    }
}
