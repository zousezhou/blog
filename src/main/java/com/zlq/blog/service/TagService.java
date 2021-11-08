package com.zlq.blog.service;

import com.zlq.blog.pojo.Tag;
import com.zlq.blog.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Create by lanqzhou on 2020.10.26
 */


public interface TagService {

    Tag saveTag(Tag tag);

    void deleteTag(Long id);

    Tag updateTag(Long id,Tag tag);

    Tag getTag(Long id);

    Page<Tag> listTag(Pageable pageable);

    List<Tag> listTag();

}
