package com.rh.blog.service;

import com.rh.blog.pojo.Blog;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

public interface BlogService {

    List<Blog> findAllBlog();

    void insertBlog(Blog blog);

    Blog findOne(Long id);

    void updateBlog(Blog blog);

    void deleteBlog(Long id);

    int countBlog();

    List<Blog> recommendBlog();

    Blog getAndConvert(Long id);

    List<Blog> findTypesBlog(Long type_id);

    List<Blog> findTagsBlog(Long tag_id);

    List<Blog> likeBlog(String content);
}
