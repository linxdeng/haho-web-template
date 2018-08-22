package cn.haho.template.service.admin.impl;

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
}
