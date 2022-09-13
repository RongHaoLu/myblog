package com.rh.blog.service;

import com.rh.blog.pojo.Tag;

import java.util.List;

public interface TagsService {

    List<Tag> findAllTag();

    void insertTag(Tag tag);

    void updateTag(Tag tag);

    void deleteTag(Long id);

    int countTag();
}
