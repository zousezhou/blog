package com.zlq.blog.service;

import com.zlq.blog.pojo.Blog;
import com.zlq.blog.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Create by lanqzhou on 2020.10.26
 */


public interface BlogService {

    Blog saveBlog(Blog blog);

    void deleteBlog(Long id);

    Blog getBlog(Long id);

    Page<Blog> listBlog(Pageable pageable);

}
