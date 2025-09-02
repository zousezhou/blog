package com.zlq.blog.service.imp;

import com.zlq.blog.dto.TypeRepository;
import com.zlq.blog.exception.IllegalOperationException;
import com.zlq.blog.pojo.Type;
import com.zlq.blog.service.TypeService;
import com.zlq.blog.web.admin.LoginController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by lanqzhou on 2020.10.26
 */

@Service
public class TypeServiceImp implements TypeService {

    @Autowired
    private TypeRepository typeRepository;


    @Transactional
    @Override
    public Type saveType(Type type) {//type更新和新增的  service
        Type t = null;
        type.setUserId(LoginController.userId);
        t = typeRepository.findByNameAndUserId(type.getName(),LoginController.userId);

        if (t != null && t.getId() != type.getId()) {
            throw new IllegalOperationException("该分类已存在，不能重复添加！");
        }
        t = typeRepository.save(type);
        if (t == null) {
            throw new IllegalOperationException("新增失败！");
        }
        return t;
    }


    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }

    /*@Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeRepository.getOne(id);
        if (t == null) {
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type, t);
        return typeRepository.save(t);
    }*/

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeRepository.getOne(id);
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAllByUserId(pageable,LoginController.userId);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAllByUserId(LoginController.userId);
    }


}
