package com.jin.service;

import com.jin.common.Result;
import com.jin.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author 子衿啊
* @description 针对表【t_user】的数据库操作Service
* @createDate 2023-05-22 13:02:46
*/
public interface UserService extends IService<User> {
    User getUser(User user);

}
