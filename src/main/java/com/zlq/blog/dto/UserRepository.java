package com.zlq.blog.dto;

import com.zlq.blog.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Create by lanqZhou on 2020.10.21
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsernameAndPassword(String username,String password);
}
