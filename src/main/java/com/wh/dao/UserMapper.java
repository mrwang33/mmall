package com.wh.dao;

import com.wh.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 */
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User user);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    //登录
    User login(@Param("username") String username, @Param("password") String password);
    //检查用户名是否存在
    int checkUsername(String username);
    //获取所有用户
    List<User> getAll();
    //检查邮箱是否存在
    int checkEmail(String email);
    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return
     */
    User selectByUsername(String username);
}