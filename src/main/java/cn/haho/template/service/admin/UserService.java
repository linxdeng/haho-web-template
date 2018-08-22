package cn.haho.template.service.admin;

import java.util.List;

import cn.haho.template.model.admin.User;

public interface UserService {
    /**
     * 查询用户列表
     * 
     * @param user
     * @return
     */
    public List<User> list(User user);
}