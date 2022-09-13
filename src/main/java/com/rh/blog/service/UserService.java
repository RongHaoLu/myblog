package com.rh.blog.service;

import com.rh.blog.pojo.User;
import org.apache.ibatis.annotations.Update;

public interface UserService {

    public User checkUser(String username,String password);

    void update_name(User user);

    void update_password(User user);
}
