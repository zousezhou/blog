package com.zlq.blog.dto;

import com.zlq.blog.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Create by lanqZhou on 2020.10.26
 */
public interface TagRepository extends JpaRepository<Tag,Long> {
    Tag findByNameAndUserId(String name,Long userId);

    List<Tag> findAllByUserId(Long userId);

    Page<Tag> findAllByUserId(Pageable pageable,Long userId);
}
