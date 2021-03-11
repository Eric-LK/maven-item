package com.eric.test;

import org.junit.Test;

import java.util.StringJoiner;

/**
 * @author liuBing
 */
public class StringTest {

    /**
     * 只允许用户输入数字1-9,字母a-z,A-Z,只能半角,不能有空格的正则表达式
     */
    public static final String ONLY_LETTER_OR_NUMBER = "^[a-z0-9A-Z]+$";

    /**
     * 对用户输入的String做校验只允许有数字和大小写字母
     * 不允许全角,只允许半角
     * <p>
     * str.matches方法,底层用的还是如下
     * Pattern.matches(regex, this)
     *
     * @param str
     * @return
     */
    public static boolean isOnlyLetterOrNumber(String str) {
        return str.matches(ONLY_LETTER_OR_NUMBER);
    }

    public static void main(String[] args) {
        String s = "231321";
        while (true){
            String a = s + "/" + s;

        }
    }

    @Test
    public void test01() {

    }

    @Test
    public void testTemp(){
        for (int i = 0; i < 1000000; i++) {
            String c = new StringBuffer().append(i).append("/").append(i).toString();
        }
    }


    @Test
    public void test02() {
        String a = "  0231313123asdAAAsa史蒂夫  ";
        System.out.println(isOnlyLetterOrNumber(a));

        ///  a.lines();
        // String c = a.stripLeading();
        //  String d = a.stripTrailing();

        // System.out.println(c);
        // System.out.println(d);

        StringJoiner stringJoiner = new StringJoiner("");

        stringJoiner.add("123");
        stringJoiner.add("456");
        System.out.printf(stringJoiner.toString());

        StringBuilder stringBuilder = new StringBuilder();

        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("123");
        stringBuffer.append("456");

        System.out.printf(stringBuffer.toString());

        //  Joiner joiner = new Joiner("");

    }


    private void printTimeStamp(String msg) {
        System.out.println(msg + System.currentTimeMillis());
    }

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
}
