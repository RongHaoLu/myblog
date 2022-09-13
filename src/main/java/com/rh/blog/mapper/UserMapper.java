package com.rh.blog.mapper;

import com.rh.blog.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select * from user where username=#{username} and password=#{password}")
    public User checkUser(String username, String password);

    @Update("update user set username=#{username},nickname=#{nickname} where id=#{id}")
    void update_name(User user);

    @Update("update user set password=#{password} where id=#{id}")
    void update_password(User user);

}
