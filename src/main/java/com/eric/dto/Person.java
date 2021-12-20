package com.eric.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liuBing
 */
@Data
public class Person implements Serializable {
    private Long id;
    private String name;
    private Integer age;
}
