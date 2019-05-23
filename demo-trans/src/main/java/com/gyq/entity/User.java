package com.gyq.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * @Auther: guanyanqi
 * @Date: 2019-05-22 11:34
 */

@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 15,nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userPwd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
