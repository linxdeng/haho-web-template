package cn.haho.template.service.admin.impl;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.haho.template.BaseTest;
import cn.haho.template.model.admin.User;
import cn.haho.template.service.admin.UserService;

public class UserServiceImplTest extends BaseTest {
    @Autowired
    UserService userService;

    @Test
    public void testList() {
        User user = new User();
        user.setId(30L);
        List<User> list = userService.list(user);
        System.out.println(list);
    }

    @Test
    public void testInsertToMongo() {
        User user = new User();
        user.setId(1L);
        user.setBirthday(new Date());
        user.setName("linx");
        userService.insertToMongo(user);
    }

    @Test
    public void testSetUserCache() {
        User user = new User();
        user.setId(1L);
        user.setBirthday(new Date());
        user.setName("linx");
        userService.setUserCache(user);
    }
}
