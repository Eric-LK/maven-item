package com.eric.reflex;

import com.eric.reflex.entity.AutowiredTest;
import com.eric.reflex.entity.Person;
import org.junit.Test;


import java.util.stream.Stream;

/*
 * 通过 注解的方式进行对象的 自动注入
 */
public class Test02 {

    @Test
    public void test() {
        Person person = new Person();
        Class<? extends Person> aClass = person.getClass();
        Stream.of(aClass.getDeclaredFields()).forEach(field -> {
            // 获取 属性上边的注解
            AutowiredTest annotation  = field.getAnnotation(AutowiredTest.class);
            // 如果注解不为空，则创建这个对象的实例
            if (annotation != null){
                field.setAccessible(true); // 提升权限
                Class<?> type = field.getType(); // 获取属性的类型
                try {
                    Object o = type.newInstance(); // 通过反射实例化
                    field.set(person,o); // 放入对应对象中
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(person.getAge());
        });
    }
}
