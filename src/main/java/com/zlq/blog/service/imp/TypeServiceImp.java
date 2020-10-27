package com.zlq.blog.service.imp;

import com.zlq.blog.dto.TypeRepository;
import com.zlq.blog.exception.NotFoundException;
import com.zlq.blog.pojo.Type;
import com.zlq.blog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Create by lanqzhou on 2020.10.26
 */

@Service
public class TypeServiceImp implements TypeService {

    @Autowired
    private TypeRepository typeRepository;


    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }


    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
       Type t = typeRepository.getOne(id);
       if (t==null){
           throw new NotFoundException("不存在该类型");
       }
       BeanUtils.copyProperties(type,t);
       return typeRepository.save(t);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeRepository.getOne(id);
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }


}
