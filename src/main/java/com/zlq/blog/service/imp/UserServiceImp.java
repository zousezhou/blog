package com.zlq.blog.service.imp;

import com.zlq.blog.dto.UserRepository;
import com.zlq.blog.pojo.User;
import com.zlq.blog.service.UserService;
import com.zlq.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create By lanqZhou on 2020.10.20
 */

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
       // User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        User user = userRepository.findByUsernameAndPassword(username, password);
        return user;
    }

    @Override
    public User checkUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

    @Override
    public User saveUser(User user) {
        userRepository.save(user);
        return user;
    }

}
