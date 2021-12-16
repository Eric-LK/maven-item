package com.eric.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eric.entity.Student;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * 测试DAO
 *
 * @author  liuBing
 * @Date 2019/10/28  15:14
 */
public interface StudentMapper extends BaseMapper<Student> {

     /*
      * @Param 多参数指定参数名字，否则会被封装成map中的param1，param2 。。。
      * */
     List<Student> selectPerson(String id);

     Boolean insertPerson(Student person);

     // 当返回类型是map的时候，告诉mybatis用什么字段作为map的key
     @MapKey("id")
     Map<Integer, Student> selectPersonMapKey(String name);

     Student selectPersonSchool(String id);

     Map<String,Object> selectPersonByIdAndAge(@Param("id") String id,@Param("age") String age);

     Boolean updatePerson(@Param("person") Student person);

     @Update(" update user set name = #{userName} where id = #{userId}")
     int updateUserName(Integer userId,String userName);
}
