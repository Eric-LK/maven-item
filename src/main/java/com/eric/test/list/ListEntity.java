package com.eric.test.list;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author liuBing
 */
@Data
@AllArgsConstructor
public class ListEntity {
    private long id;
    private String key;
    private String value;
}
