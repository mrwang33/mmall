package com.wh.service;

import com.wh.common.ServerResponse;
import com.wh.pojo.User;

/**
 * Created by wh on 17-7-7.
 */
public interface IUserService {
    ServerResponse<User> login(String username,String password);
}
