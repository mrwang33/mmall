package com.wh.controller.portal;

import com.wh.common.ServerResponse;
import com.wh.pojo.User;
import com.wh.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by wh on 17-7-7.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService iUserService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ServerResponse<User> login(String username, String password, HttpSession session) {
        ServerResponse<User> login = iUserService.login(username, password);
        if (login.isSuccess()) {
            session.setAttribute("user",login.getData());
        }
        return login;
    }
}
