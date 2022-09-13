package com.rh.blog.mapper;

import com.rh.blog.pojo.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TagsMapper {

    @Select("select * from tag order by tag_create_time desc")
    List<Tag> findAllTag();

    @Insert("insert into tag(tag_name,tag_create_time) value(#{tag_name},now())")
    void insertTag(Tag tag);

    @Update("update tag set tag_name=#{tag_name} where tag_id=#{tag_id}")
    void updateTag(Tag tag);

    @Delete("delete from tag where tag_id=#{tag_id}")
    void deleteTag(Long id);

    @Select("select count(tag_id) from tag")
    int countTag();
}
