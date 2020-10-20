package com.zlq.blog.service;


import com.zlq.blog.pojo.User;

/**
 * Create by lanqZhou on 2020.10.20
 */
public interface UserService {

    User checkUser(String username, String password);
}
