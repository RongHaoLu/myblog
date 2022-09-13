package com.rh.blog.service;

import com.rh.blog.mapper.UserMapper;
import com.rh.blog.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User checkUser(String username, String password) {

        User user = userMapper.checkUser(username, DigestUtils.md5DigestAsHex(password.getBytes()));
        return user;
    }

    @Override
    public void update_name(User user) {
        userMapper.update_name(user);
    }

    @Override
    public void update_password(User user) {
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userMapper.update_password(user);
    }
}
