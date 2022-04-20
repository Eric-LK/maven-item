package com.eric.controller;

import com.eric.dao.StudentMapper;
import com.eric.entity.Student;
import com.eric.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuBing
 */
@Api("MyBatisController")
@RestController
@RequestMapping
@Slf4j
public class MyBatisController {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentService studentService;

    @ApiOperation("insertStudent")
    @GetMapping("/insertStudent")
    public boolean insertStudent(Integer id, String name) {
        Student student = new Student();
        student.setId(id);
        log.info(String.valueOf(name.length()));

        if (name.length() > 8) {
            return false;
        }
        student.setName(name);

        studentMapper.insert(student);

        return true;
    }

    @ApiOperation("exceptionTest")
    @GetMapping("/exceptionTest")
    public boolean exceptionTest() {
        throw new RuntimeException();
        //return true;
    }




    @ApiOperation("list")
    @GetMapping("list")
    public void list(@RequestParam Integer id){
        studentService.listTest(id);
    }
}
