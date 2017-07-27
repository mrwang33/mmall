package com.wh.controller.portal;

import com.wh.common.Const;
import com.wh.common.ServerResponse;
import com.wh.pojo.User;
import com.wh.service.IUserService;
import org.springframework.stereotype.Controller;
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
    @ResponseBody
    @RequestMapping(value = "/checkUsername",method = RequestMethod.POST)
    public ServerResponse<String> checkUsername(String username) {
        return iUserService.verify(username, Const.USERNAME);
    }

    //检查邮箱是否存在
    @ResponseBody
    @RequestMapping(value = "/checkEmail",method = RequestMethod.POST)
    public ServerResponse<String> checkEmail(String email) {
        return iUserService.verify(email, Const.EMAIL);
    }

    /**
     * 修改密码
     * @param oldPassword 旧密码
     * @param password 新密码
     * @param passwordConfirm 确认新密码
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/modifyPassword",method = RequestMethod.POST)
    public ServerResponse<String> modifyPassword(String oldPassword,String password,String passwordConfirm,HttpSession session) {
        if (password==null||!password.equals(passwordConfirm)) {
            return ServerResponse.createByErrorMessage("两次密码不一样！");
        }
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser==null) {
            return ServerResponse.createByErrorMessage("尚未登录!");
        }
        return iUserService.modifyPassword(currentUser.getId(),oldPassword,password);
    }

    /**
     * 重置密码
     * @param username 用户名
     * @param answer 问题回答
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "resetPassword",method = RequestMethod.POST)
    public ServerResponse<String> resetPassword(String username,String answer) {
        return iUserService.resetPassword(username,answer);
    }

    /**
     * 获取重置密码问题
     * @param username
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getQuestion",method = RequestMethod.POST)
    public ServerResponse<String> getQuestion(String username) {
        return iUserService.getQuestion(username);
    }


}
