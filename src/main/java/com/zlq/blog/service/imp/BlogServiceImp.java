package com.zlq.blog.service.imp;

import com.zlq.blog.dto.BlogRepository;
import com.zlq.blog.dto.TagRepository;
import com.zlq.blog.exception.IllegalOperationException;
import com.zlq.blog.pojo.Blog;
import com.zlq.blog.pojo.Tag;
import com.zlq.blog.service.BlogService;
import com.zlq.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Create by lanqzhou on 2020.10.26
 */

@Service
public class BlogServiceImp implements BlogService {

    @Autowired
    BlogRepository blogRepository;

    @Override
    public Blog saveBlog(Blog blog) {
        return null;
    }

    @Override
    public void deleteBlog(Long id) {

    }

    @Override
    public Blog getBlog(Long id) {
        return null;
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }
}
