package com.rh.blog.service;

import com.rh.blog.mapper.TagsMapper;
import com.rh.blog.pojo.Tag;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TagsServiceImpl implements TagsService{

    @Resource
    private TagsMapper tagsMapper;

    @Override
    public List<Tag> findAllTag() {
        return tagsMapper.findAllTag();
    }

    @Override
    public void insertTag(Tag tag) {

        tagsMapper.insertTag(tag);
    }

    @Override
    public void updateTag(Tag tag) {

        tagsMapper.updateTag(tag);
    }

    @Override
    public void deleteTag(Long id) {

        tagsMapper.deleteTag(id);
    }

    @Override
    public int countTag() {

        return tagsMapper.countTag();
    }
}
