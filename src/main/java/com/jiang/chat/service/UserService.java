package com.jiang.chat.service;

import com.jiang.chat.bean.User;

public interface UserService {

    /**
     * 根据Id查询用户
     * @param id
     * @return
     */
    public User queryById(String id);



}
