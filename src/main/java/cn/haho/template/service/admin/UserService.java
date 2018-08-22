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

    /**
     * 将数据插入mongodb
     * 
     * @param user
     */
    public void insertToMongo(User user);

    /**
     * 设置user缓存
     */
    public void setUserCache(User user);
}
