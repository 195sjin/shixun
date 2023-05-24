package com.jin.controller;

import com.jin.common.BaseContext;
import com.jin.common.Result;
import com.jin.pojo.User;
import com.jin.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @Author xiaojin
 * @Date 2023/5/22 13:11
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public Result<Object> login(@RequestBody User user,HttpServletRequest request){
        User loginUser = userService.getUser(user);
        String name = loginUser.getName();

        if (loginUser != null){
            request.getSession().setAttribute("name",name);

            return Result.success(loginUser);
        }
        return Result.fail(20001,"账号或密码错误");
    }


}
