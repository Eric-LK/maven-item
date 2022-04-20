package com.eric.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eric.dao.StudentMapper;
import com.eric.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description：
 * @Author: liuBing
 * @DateTime: 2022/1/18 14:45
 */
@Service
@Slf4j
public class StudentService extends ServiceImpl<StudentMapper, Student> {

    public void listTest(Integer id){

        List<Student> list = lambdaQuery().eq(Student::getId, id).list();
        log.info("测试，如果list查不到，返回的是：{}",list);
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis() / 1000 + 60 * 60 * 24 * 365 * 5 );
    }
}
