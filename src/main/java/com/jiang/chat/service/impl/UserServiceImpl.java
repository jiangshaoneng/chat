package com.jiang.chat.service.impl;

import com.jiang.chat.bean.User;
import com.jiang.chat.mapper.UserMapper;
import com.jiang.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

}
