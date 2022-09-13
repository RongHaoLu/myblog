package com.rh.blog.service;

import com.rh.blog.handler.NotFoundException;
import com.rh.blog.mapper.BlogMapper;
import com.rh.blog.pojo.Blog;
import com.rh.blog.util.MarkdownUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogMapper blogMapper;

    @Override
    public List<Blog> findAllBlog() {

        return blogMapper.findAllBlog() ;
    }

    @Override
    public void insertBlog(Blog blog) {

        blogMapper.insertBlog(blog);
    }

    @Override
    public Blog findOne(Long id) {

        Blog blog = blogMapper.findOne(id);

        if(blog==null){
            throw new NotFoundException("该博客不存在");
        }

        Blog b = new Blog();
        b=blog;

        String content = b.getContent();
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));

        return b;
    }

    @Override
    public void updateBlog(Blog blog) {
        blogMapper.updateBlog(blog);
    }

    @Override
    public void deleteBlog(Long id) {
        blogMapper.deleteBlog(id);
    }

    @Override
    public int countBlog() {

        return blogMapper.countBlog();
    }

    @Override
    public List<Blog> recommendBlog() {
        return blogMapper.recommendBlog();
    }

    @Override
    public Blog getAndConvert(Long id) {

        Blog blog = blogMapper.findOne(id);
        if(blog==null){
            throw new NotFoundException("该博客不存在");
        }

        Blog b = new Blog();
        b=blog;

        String content = b.getContent();
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return b;
    }

    @Override
    public List<Blog> findTypesBlog(Long type_id) {
        return blogMapper.findTypesBlog(type_id);
    }

    @Override
    public List<Blog> findTagsBlog(Long tag_id) {
        return blogMapper.findTagsBlog(tag_id);
    }

    @Override
    public List<Blog> likeBlog(String content) {
        return blogMapper.likeBlog(content);
    }
}
