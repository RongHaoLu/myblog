package com.rh.blog.mapper;

import com.rh.blog.pojo.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TypeMapper {

    @Insert("insert into type(name,create_time) value(#{name},now())")
    void saveType(Type type);

    Type findOne(Long id);

    @Select("select * from type order by create_time desc")
    List<Type> findAllType();

    @Select("select count(id) from type")
    int countType();

    @Update("update type set name=#{name} where id=#{id}")
    void updateType(Type type);

    @Delete("delete from type where id=#{id}")
    void deleteType(Long id);


}
