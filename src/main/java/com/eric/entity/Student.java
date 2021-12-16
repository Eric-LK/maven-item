package com.eric.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author lk
 */
@Data
@TableName(value = "test_student", autoResultMap = true)
public class Student {
    @ApiModelProperty(value = "主键id")
    @TableId(type = IdType.ASSIGN_ID,value = "id")
    private Integer id;
    @TableField(value = "name")
    private String name;
    private Integer age;
    private String sex;
    private String school_id;
    @TableField(fill = FieldFill.INSERT)
    private String createTime;
}
