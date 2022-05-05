package com.eric.test;


import com.eric.dto.Person;
import org.junit.Test;

/**
 * @author liuBing
 */
public class StringTest {

    @Test
    public void test03(){
        // 字符串重复三次
        // System.out.println("123".repeat(3));

        // 把所有 1 替换成 2
        System.out.println("123".replace('1','2'));


        // 先比较长度：
        // 长度不一样，返回长度的差值
        // 长度一样，返回不同字符 ASCII 码的差值
        // 长度和字符都一样，返回 0
        System.out.println("123".compareTo("3211"));
        System.out.println("123".compareTo("123"));
        System.out.println("123".compareTo("13"));
        System.out.println("123".compareTo("120"));
    }

    @Test
    public void test04(){
        String a = "1231";
        Person b = new Person();
        b.setName("1231");
        System.out.println(a.equals(b.getName()));

        // 输出 true
    }
}
