package com.gyq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyq.dao.UserDao;
import com.gyq.entity.User;
import com.gyq.service.UserService;

/**
 * @Auther: guanyanqi
 * @Date: 2019-05-22 11:53
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public boolean login(String userName, String userPwd) {
        User user = userDao.findByUserName(userName);
        if (user == null ){
            return false;
        }
        return userPwd.equals(user.getUserPwd());
    }
}
