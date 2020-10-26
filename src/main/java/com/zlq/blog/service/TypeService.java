package com.zlq.blog.service;

import com.zlq.blog.pojo.Type;
import org.springframework.data.domain.Page;
import java.awt.print.Pageable;

/**
 * Create by lanqzhou on 2020.10.26
 */
public interface TypeService {

    Type saveType(Type type);

    void deleteType(Long id);

    Type updateType(Long id,Type type);

    Type getType(Long id);

    Page<Type> listType(Pageable pageable);

}
