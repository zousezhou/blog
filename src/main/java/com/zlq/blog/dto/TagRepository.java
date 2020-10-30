package com.zlq.blog.dto;

import com.zlq.blog.pojo.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Create by lanqZhou on 2020.10.26
 */
public interface TagRepository extends JpaRepository<Tag,Long> {
    Tag findByName(String name);
}
