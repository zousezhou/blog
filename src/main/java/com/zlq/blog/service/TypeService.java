package com.zlq.blog.service;

import com.zlq.blog.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Create by lanqzhou on 2020.10.26
 */


public interface TypeService {

    Type saveType(Type type);

    void deleteType(Long id);

    Type getType(Long id);

    Page<Type> listType(Pageable pageable);

    List<Type> listType();
}
