package com.zlq.blog.dto;

import com.zlq.blog.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Create by lanqZhou on 2020.10.26
 */
public interface TypeRepository extends JpaRepository<Type,Long> {
    Type findByNameAndUserId(String name,Long userId);

    Page<Type> findAllByUserId(Pageable pageable, Long userId);


}
