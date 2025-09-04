package com.zlq.blog.service.imp;

import com.zlq.blog.dto.BlogRepository;
import com.zlq.blog.exception.IllegalOperationException;
import com.zlq.blog.pojo.Blog;
import com.zlq.blog.pojo.BlogQuery;
import com.zlq.blog.pojo.Type;
import com.zlq.blog.pojo.User;
import com.zlq.blog.service.BlogService;
import com.zlq.blog.service.TagService;
import com.zlq.blog.util.StringUtils;
import com.zlq.blog.web.admin.LoginController;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    TagService tagService;

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        Long userId = LoginController.userId;
        if (null == userId){
            throw new IllegalOperationException("登录信息过期！");
        }
        blog.setUpdateTime(new Date());
        String tagIds = blog.getTagIds();
        if (StringUtils.isNotEmpty(tagIds)){
            blog.setTagList(tagService.listTagByIds(tagIds));
        }
        if (null == blog.getId()){//博客ID为空时，说明是新增博客，不是博客修改
            User user = new User();
            user.setId(userId);
            blog.setUser(user);
            blog.setCreateTime(new Date());

        }else{
            //当前方法存在强耦合,因为当博客表新增字段且该字段不是博客修改页面上的属性时，需要在这里添加，不然博客修改的时候可能会置空
            Blog b = blogRepository.getOne(blog.getId());
            blog.setCreateTime(b.getCreateTime());
            blog.setViews(b.getViews());
        }

        return blogRepository.save(blog);
    }

    @Override
    public Blog updateBlog(Blog blog, Long id) {
        Blog b = blogRepository.getOne(id);
        if (null == b){
            throw new IllegalOperationException("博客不存在！");
        }
        BeanUtils.copyProperties(blog, b);
        return blogRepository.save(b);
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
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blogQuery) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.isNotEmpty(blogQuery.getTitle())) {
                    predicates.add(criteriaBuilder.like(root.<String>get("title"), "%" + blogQuery.getTitle() + "%"));
                }
                if (null != blogQuery.getTypeId()) {
                    predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"), blogQuery.getTypeId()));
                }
                if (null != blogQuery.getRecommend() && blogQuery.getRecommend()){
                    predicates.add(criteriaBuilder.equal(root.<Boolean>get("recommend"), blogQuery.getRecommend()));
                }
                predicates.add(criteriaBuilder.equal(root.<User>get("user").get("id"), LoginController.userId));
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        }, pageable);

    }


}
