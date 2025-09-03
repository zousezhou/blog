package com.zlq.blog.service.imp;

import com.zlq.blog.dto.BlogRepository;
import com.zlq.blog.exception.IllegalOperationException;
import com.zlq.blog.pojo.Blog;
import com.zlq.blog.pojo.BlogQuery;
import com.zlq.blog.pojo.Type;
import com.zlq.blog.pojo.User;
import com.zlq.blog.service.BlogService;
import com.zlq.blog.util.StringUtils;
import com.zlq.blog.web.admin.LoginController;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Create by lanqzhou on 2020.10.26
 */

@Service
public class BlogServiceImp implements BlogService {

    @Autowired
    BlogRepository blogRepository;

    @Override
    public Blog saveBlog(Blog blog) {
        User user = new User();
        user.setId(LoginController.userId);
        if (null == blog.getId()){
            blog.setCreateTime(new Date());
            blog.setUser(user);
        }
        blog.setUpdateTime(new Date());
        return blogRepository.save(blog);
    }

    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getOne(id);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.isNotEmpty(blog.getTitle())) {
                    predicates.add(criteriaBuilder.like(root.<String>get("title"), "%" + blog.getTitle() + "%"));
                }
                if (blog.getTypeId() != null) {
                    predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
                }
                if (null != blog && null != blog.getRecommend() && blog.getRecommend()){
                    predicates.add(criteriaBuilder.equal(root.<Boolean>get("recommend"), blog.getRecommend()));
                }
                predicates.add(criteriaBuilder.equal(root.<User>get("user").get("id"), LoginController.userId));
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        }, pageable);

    }

    @Override
    public Blog updateBlog(Blog blog, Long id) {
        Blog b = null;
        b = blogRepository.getOne(id);
        if (b != null) {
            throw new IllegalOperationException("博客已存在！");
        }
        BeanUtils.copyProperties(blog, b);
        return blogRepository.save(b);
    }

}
