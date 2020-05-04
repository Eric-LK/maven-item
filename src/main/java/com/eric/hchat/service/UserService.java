package com.eric.hchat.service;

import com.eric.hchat.pojo.TbUser;
import com.eric.hchat.pojo.vo.User;

import java.util.List;

public interface UserService {
    List<TbUser> selectAll();

    User login(String userName, String password);

    void register(TbUser tbUser);
}
