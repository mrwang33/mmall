package com.wh.controller.portal;

import com.wh.common.ServerResponse;
import com.wh.pojo.User;
import com.wh.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by wh on 17-7-7.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService iUserService;
    //登录
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ServerResponse<User> login(String username, String password, HttpSession session) {
        ServerResponse<User> login = iUserService.login(username, password);
        if (login.isSuccess()) {
            session.setAttribute("user",login.getData());
        }
        return login;
    }
    @ResponseBody
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public ServerResponse<List<User>> getAll() {
        return iUserService.getAll();
    }

    //注销
    @ResponseBody
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public ServerResponse<String> logout(HttpSession session) {
        session.removeAttribute("user");
        return ServerResponse.createBySuccessMessage("注销成功");
    }

    //注册
    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ServerResponse<String> register(User user) {
        return iUserService.register(user);
    }

    //检查用户名是否存在
}
