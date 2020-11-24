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

    @Tolerate
    public Person(){}
}
