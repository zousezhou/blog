package com.zlq.blog.dto;

import com.zlq.blog.pojo.Type;
import com.zlq.blog.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Create by lanqZhou on 2020.10.26
 */
public interface TypeRepository extends JpaRepository<Type,Long> {

}
