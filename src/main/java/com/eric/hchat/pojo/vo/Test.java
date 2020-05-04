package com.eric.hchat.pojo.vo;

public class Test {
    public static void main(String[] args) {
        User u = User.builder().id("123").username("士大夫").build();
        System.out.println(u);
    }
}
