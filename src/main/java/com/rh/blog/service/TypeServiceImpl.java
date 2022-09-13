package com.rh.blog.service;

import com.rh.blog.handler.NotFoundException;
import com.rh.blog.mapper.TypeMapper;
import com.rh.blog.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Resource
    TypeMapper typeMapper;

    @Transactional
    @Override
    public void saveType(Type type) {
        typeMapper.saveType(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeMapper.findOne(id);
    }

    @Override
    public List<Type> findAllType() {
        return typeMapper.findAllType();
    }

    @Override
    public int countType() {
        return typeMapper.countType();
    }

    @Transactional
    @Override
    public void updateType(Type type) {

        typeMapper.updateType(type);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {

        typeMapper.deleteType(id);
    }
}
