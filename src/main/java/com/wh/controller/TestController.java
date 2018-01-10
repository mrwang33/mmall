package com.wh.controller;

import com.wh.dao.UserMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by wh on 17-7-6.
 */
@RestController
public class TestController {
    @Resource
    private UserMapper userMapper;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public Object test() {
        System.out.printf("test");
        return "hello world";
    }
}
