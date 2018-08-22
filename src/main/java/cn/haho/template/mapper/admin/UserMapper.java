package cn.haho.template.mapper.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.haho.template.model.admin.User;

@Mapper
public interface UserMapper {

    /**
     * 查询用户列表
     * 
     * @param user
     * @return List<User>
     */
    public List<User> list(User user);
}
