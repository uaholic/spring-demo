package com.gyq.dao;

import org.springframework.data.repository.CrudRepository;

import com.gyq.entity.User;

/**
 * @Auther: guanyanqi
 * @Date: 2019-05-22 11:50
 */
public interface UserDao extends CrudRepository<User,Integer> {

    User findByUserName(String username);

}