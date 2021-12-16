package com.eric.entity;

import lombok.Data;

import java.util.List;

@Data
public class School {
    private String id;
    private String school_id;
    private String name;
    private List<Student> personList;
}
