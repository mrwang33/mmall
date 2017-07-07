package com.wh.dao;

import com.wh.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    //登录
    User login(@Param("username") String username, @Param("password") String password);
    //检查用户名是否存在
    int checkUsername(String username);
}