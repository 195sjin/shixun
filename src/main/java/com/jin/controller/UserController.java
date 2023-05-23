package com.jin.controller;

import com.jin.common.Result;
import com.jin.pojo.User;
import com.jin.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author xiaojin
 * @Date 2023/5/22 13:11
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public Result<Object> login(@RequestBody User user){
        User loginUser = userService.getUser(user);
        if (loginUser != null){
            return Result.success(loginUser);
        }
        return Result.fail(20001,"账号或密码错误");
    }


}
