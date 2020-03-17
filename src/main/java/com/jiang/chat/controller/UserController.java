package com.jiang.chat.controller;

import com.jiang.chat.bean.User;
import com.jiang.chat.utils.MSG;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: jiang
 * \* Date: 2019/8/3
 * \* Time: 17:08
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@RestController
@RequestMapping("/u")
public class UserController {

    Logger logger = Logger.getLogger(UserController.class);

    /**
     * 模拟登录
     * @param user
     * @return
     */
    @PostMapping("/registOrLogin")
    public MSG registOrLogin(@RequestBody User user){

        if(StringUtils.isBlank(user.getUsername())  || StringUtils.isBlank(user.getPassword())){
            return MSG.error("用户名或密码不能为空!");
        }
        logger.debug(user.getUsername());
        return MSG.success().add("user",user);
    }

}