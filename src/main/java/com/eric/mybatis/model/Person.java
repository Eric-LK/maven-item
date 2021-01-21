package com.eric.mybatis.model;

import com.eric.mybatis.model.School;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

// @TableName(value = "person") // 对应数据库的表名,名字一致可以不写

@ApiModel(value = "个人信息表") // 用于swagger显示
@NoArgsConstructor // 自动生成无参数构造函数
@AllArgsConstructor // 自动生成全参数构造函数
@Data // 自动为所有字段添加@ToString, @EqualsAndHashCode, @Getter方法，为非final字段添加@Setter
@Accessors(chain = true) // 可写可不写，关于构造函数的注解
public class Person {
    @ApiModelProperty(value = "主键id") //swagger
    // @TableId(type = IdType.UUID,value = "id") // type的id主键类型，对应数据表的字段名
    // @Column
    private Integer id;
    // @TableField(value = "name") // 非id属性对应数据库的字段 ；exist 为 false 则说明它不对应数据库的一个字段
    private String name;
    private Integer age;
    private String sex;
    private School school;
}
