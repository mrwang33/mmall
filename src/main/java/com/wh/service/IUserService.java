package com.wh.service;

import com.wh.common.ServerResponse;
import com.wh.pojo.User;
import net.sf.jsqlparser.schema.Server;

import java.util.List;

/**
 * Created by wh on 17-7-7.
 */
public interface IUserService {
    //登录
    ServerResponse<User> login(String username,String password);
    //获取所有用户
    ServerResponse<List<User>> getAll();
    //注册
    ServerResponse<String> register(User user);
    //验证数据是否有效
    ServerResponse<String> verify(String information,String type);
}
