package com.rh.blog.mapper;

import com.rh.blog.pojo.Blog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BlogMapper {

    List<Blog> findAllBlog();

    @Insert("insert into blog" +
            " (content,create_time,first_picture,flag,published,recommend,title,update_time,views,type_id,user_id,commentabled,description)" +
            " value(#{content},now(),#{first_picture},#{flag},#{published},#{recommend},#{title},now(),#{views},#{type_id},#{user_id},#{commentabled},#{description})")
    void insertBlog(Blog blog);

    Blog findOne(Long id);

    void updateBlog(Blog blog);

    @Delete("delete from blog where id=#{id}")
    void deleteBlog(Long id);

    @Select("select count(id) from blog")
    int countBlog();

    @Select("select * from blog where recommend=1 order by create_time desc")
    List<Blog> recommendBlog();

    List<Blog> findTypesBlog(Long type_id);

    List<Blog> findTagsBlog(Long tag_id);

    List<Blog> likeBlog(String content);
}
