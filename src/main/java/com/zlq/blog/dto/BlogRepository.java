package com.zlq.blog.dto;

import com.zlq.blog.pojo.Blog;
import com.zlq.blog.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Create by lanqZhou on 2020.11.4
 */
public interface BlogRepository extends JpaRepository<Blog,Long> {
    Page<Blog> findAll(Specification<Blog> blogSpecification, Pageable pageable);
}
