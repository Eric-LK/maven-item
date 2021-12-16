package com.eric.mybatis.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * @author lk
 *
 */

// 用于swagger显示
@ApiModel(value = "个人信息表")
// 自动生成无参数构造函数
@NoArgsConstructor
// 自动生成全参数构造函数
@AllArgsConstructor
// 自动为所有字段添加@ToString, @EqualsAndHashCode, @Getter方法，为非final字段添加@Setter
@Data
// 可写可不写，关于构造函数的注解
@Accessors(chain = true)
public class Student {
    /**
     * type的id主键类型，对应数据表的字段名
     * @TableId(type = IdType.UUID,value = "id")
     * @Column
     */
    @ApiModelProperty(value = "主键id") //swagger
    private Integer id;

    /**
     * @TableField(value = "name")
     * 非id属性对应数据库的字段
     * exist 为 false 则说明它不对应数据库的一个字段
     */
    private String name;
    private Integer age;
    private String sex;
    private School school;

    private String createTime;
}
