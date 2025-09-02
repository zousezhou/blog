package com.zlq.blog.service.imp;

import com.zlq.blog.dto.TagRepository;
import com.zlq.blog.exception.IllegalOperationException;
import com.zlq.blog.exception.NotFoundException;
import com.zlq.blog.pojo.Tag;
import com.zlq.blog.service.TagService;
import com.zlq.blog.web.admin.LoginController;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Create by lanqzhou on 2020.10.26
 */

@Service
public class TagServiceImp implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Tag saveTag(Tag tag) {
        Tag t = tagRepository.findByNameAndUserId(tag.getName(), LoginController.userId);
        if (t != null && t.getId() != tag.getId()) {
            throw new IllegalOperationException("该标签已存在，不能重复添加！");
        }
        t = tagRepository.save(tag);
        if (t == null) {
            throw new IllegalOperationException("新增失败！");
        }
        return t;
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    /*@Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagRepository.getOne(id);
        if (t == null) {
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(tag, t);
        return tagRepository.save(t);
    }*/

    @Override
    public Tag getTag(Long id) {
        return tagRepository.getOne(id);
    }

    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return (Page<Tag>) tagRepository.findAllByUserId(pageable,LoginController.userId);
    }

    @Override
    public List<Tag> listTag() {
        return (List<Tag>) tagRepository.findAllByUserId(LoginController.userId);
    }


}
