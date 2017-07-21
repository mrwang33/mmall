package com.wh.service.impl;

import com.wh.common.Const;
import com.wh.common.ServerResponse;
import com.wh.dao.UserMapper;
import com.wh.pojo.User;
import com.wh.service.IUserService;
import com.wh.util.MD5Util;
import net.sf.jsqlparser.schema.Server;
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

    @Override
    public ServerResponse<String> register(User user) {
        ServerResponse<String> verifyUsername = verify(user.getUsername(), Const.USERNAME);
        if (!verifyUsername.isSuccess()) {
            return verifyUsername;
        }
        ServerResponse<String> verifyEmail = verify(user.getEmail(), Const.EMAIL);
        if (!verifyEmail.isSuccess()) {
            return verifyEmail;
        }
        //将密码进行md5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        int insert = userMapper.insert(user);
        if (insert<=0) {
            return ServerResponse.createByErrorMessage("注册失败！请稍后重试，或联系网站管理员");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    @Override
    public ServerResponse<String> verify(String information, String type) {
        if (Const.EMAIL.equals(type)) {
            if (userMapper.checkEmail(information)>0) {
                return ServerResponse.createByErrorMessage("邮箱已存在!");
            }
        }
        if (Const.USERNAME.equals(type)) {
            if (userMapper.checkUsername(information)>0) {
                return ServerResponse.createByErrorMessage("用户名已存在!");
            }
        }
        return ServerResponse.createBySuccessMessage("验证通过!");
    }
}
