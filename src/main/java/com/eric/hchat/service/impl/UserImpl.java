package com.eric.hchat.service.impl;

import com.eric.hchat.mapper.TbUserMapper;
import com.eric.hchat.pojo.TbUser;
import com.eric.hchat.pojo.TbUserExample;
import com.eric.hchat.pojo.vo.User;
import com.eric.hchat.service.UserService;

import com.eric.hchat.utils.IdWorker;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;
import java.util.List;

@Service
@Transactional
public class UserImpl implements UserService {

    @Autowired
    TbUserMapper tbUserMapper;
    @Autowired
    IdWorker idWorker;

    public List<TbUser> selectAll(){
        return tbUserMapper.selectByExample(null);
    }

    /**
     * 登录
     * @param userName 用户名
     * @param password 密码
     * @return 注册结果
     */
    @Override
    public User login(String userName, String password) {
        //TbUser tbUser = new TbUser();
        if(StringUtils.isNoneBlank(userName) && StringUtils.isNoneBlank(userName)){
            TbUserExample example = new TbUserExample();
            TbUserExample.Criteria criteria = example.createCriteria();
            criteria.andUsernameEqualTo(userName);

            List<TbUser> userList = tbUserMapper.selectByExample(example);
            if(userList != null && userList.size() == 1) {
                // 对密码进行校验
                String encodingPassword = DigestUtils.md5DigestAsHex(password.getBytes());
                if(encodingPassword.equals(userList.get(0).getPassword())) {
                    User user = new User();
                    BeanUtils.copyProperties(userList.get(0), user);
                    return user;
                }
            }
        }
        return null;
    }

    /***
     * 注册用户
     * @param tbUser 用户信息
     */
    @Override
    public void register(TbUser tbUser) {
        try {
            // 判断用户名是否存在
            TbUserExample example = new TbUserExample();
            TbUserExample.Criteria criteria = example.createCriteria();
            criteria.andUsernameEqualTo(tbUser.getUsername());
            List<TbUser> userList = tbUserMapper.selectByExample(example);
            if (userList != null && userList.size() > 0){
                throw new RuntimeException("用户已存在");
            }
            tbUser.setId(idWorker.nextId());
            tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
            tbUser.setPicSmall("");
            tbUser.setPicNormal("");
            tbUser.setNickname(tbUser.getUsername());

            tbUserMapper.insert(tbUser);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("注册失败");
        }
    }
}
