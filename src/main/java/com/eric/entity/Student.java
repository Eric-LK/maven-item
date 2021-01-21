package com.eric.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.lang.reflect.Type;

/**
 * @author liuBing
 */
@Data
public class Student {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
}
