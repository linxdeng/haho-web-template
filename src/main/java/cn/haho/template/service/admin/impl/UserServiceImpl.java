package cn.haho.template.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import cn.haho.template.mapper.admin.UserMapper;
import cn.haho.template.model.admin.User;
import cn.haho.template.service.admin.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private StringRedisTemplate template;

    @Override
    public List<User> list(User user) {
        return userMapper.list(user);
    }

    @Override
    public void insertToMongo(User user) {
        mongoTemplate.insert(user);
    }

    @Override
    public void setUserCache(User user) {
        String key = "user_" + user.getId();
        template.opsForValue().set(key, user.toString(), 1000);
    }
}
