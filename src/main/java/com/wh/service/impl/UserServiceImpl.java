package com.wh.service.impl;

import com.wh.common.ServerResponse;
import com.wh.dao.UserMapper;
import com.wh.pojo.User;
import com.wh.service.IUserService;
import com.wh.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wh on 17-7-7.
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService{
    @Resource
    private UserMapper userMapper;

    @Override
    public ServerResponse<User> login(String username, String password) {
        //检查用户是否存在
        int i = userMapper.checkUsername(username);
        if (i==0) {
            return ServerResponse.createByErrorMessage("用户名不存在！");
        }
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        User login = userMapper.login(username, md5Password);
        if (login==null) {
            return ServerResponse.createByErrorMessage("密码错误！");
        }
        //将密码设为空 避免controller将密码放在session中
        login.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功",login);
    }

    @Override
    public ServerResponse<List<User>> getAll() {
        ServerResponse<List<User>> sr = ServerResponse.createBySuccess(userMapper.getAll());
        return sr;
    }
}
