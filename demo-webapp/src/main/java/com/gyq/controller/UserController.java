package com.gyq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gyq.service.UserService;
import com.gyq.vo.JsonResult;

/**
 * @Auther: guanyanqi
 * @Date: 2019-05-22 14:36
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public JsonResult login(String userName,String userPwd){
        try {
            boolean success = userService.login(userName, userPwd);
            return new JsonResult.Builder().errorCode(success?0:-1).message(success?"登陆成功":"登陆失败").build();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
