package com.eric.reflex.entity;



public class Person {
    @AutowiredTest
    private Age age;

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }
}
