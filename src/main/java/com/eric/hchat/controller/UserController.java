package com.eric.hchat.controller;

import com.eric.hchat.pojo.TbUser;
import com.eric.hchat.pojo.vo.Result;
import com.eric.hchat.pojo.vo.User;
import com.eric.hchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("all")
    public List<TbUser> findAll(){
        return userService.selectAll();
    }

    @GetMapping("login")
    private Result login(String userName,String password){
        try {
            User _user = userService.login(userName,password);
            if (_user == null){
                return new Result(false,"登录失败");
            }
            return new Result(true,"登录成功！",_user);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"登录失败");
        }
    }

    @PostMapping("register")
    private Result register(@RequestBody TbUser tbUser){
        try {
            userService.register(tbUser);
            return new Result(true,"注册成功");
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new Result(false,e.getMessage());
        }
    }
}
