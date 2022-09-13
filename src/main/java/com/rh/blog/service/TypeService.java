package com.rh.blog.service;

import com.github.pagehelper.Page;
import com.rh.blog.pojo.Type;

import java.util.List;


public interface TypeService {

    void saveType(Type type);

    Type getType(Long id);

    List<Type> findAllType();

    int countType();

    void updateType(Type type);

    void deleteType(Long id);

}
