package com.jin.service;

import com.jin.pojo.MovieType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 子衿啊
* @description 针对表【t_movie_type】的数据库操作Service
* @createDate 2023-05-22 13:02:38
*/
public interface MovieTypeService extends IService<MovieType> {

    void select(String movieType);

    void updateTwo(Integer id, String movieType);

    void removeType(Integer id);

}
