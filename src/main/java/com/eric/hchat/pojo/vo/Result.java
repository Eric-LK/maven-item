package com.eric.hchat.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
public class Result {
    private boolean success;
    private String message;
    private Object result;

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
