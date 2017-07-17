package com.wh.service;

import com.wh.common.ServerResponse;
import com.wh.pojo.User;

import java.util.List;

/**
 * Created by wh on 17-7-7.
 */
public interface IUserService {
    //登录
    ServerResponse<User> login(String username,String password);
    //获取所有用户
    ServerResponse<List<User>> getAll();
}
