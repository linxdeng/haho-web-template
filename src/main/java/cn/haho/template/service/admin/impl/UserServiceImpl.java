package cn.haho.template.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.haho.template.mapper.admin.UserMapper;
import cn.haho.template.model.admin.User;
import cn.haho.template.service.admin.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> list(User user) {
        return userMapper.list(user);
    }

}
