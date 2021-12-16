package com.eric.mybatis.model;

import lombok.Data;

import java.util.List;

@Data
public class School {
    private String id;
    private String name;
    private List<Student> personList;
}
