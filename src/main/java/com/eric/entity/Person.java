package com.eric.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import lombok.experimental.Tolerate;

import java.io.Serializable;

/**
 * @author liuBing
 */
@Builder
@Getter
@Setter
@Data
public class Person implements Serializable {
    private Long id;
    private String name;
    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Tolerate
    public Person(){}
}
