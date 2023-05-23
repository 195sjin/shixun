package com.jin.service;

import com.jin.pojo.Movie;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jin.pojo.News;

import java.util.Map;

/**
* @author 子衿啊
* @description 针对表【t_movie】的数据库操作Service
* @createDate 2023-05-22 13:02:32
*/
public interface MovieService extends IService<Movie> {


    void change(Integer id, News news);

    void saveNews(Integer id,News news);

    void saveAndChange(Movie movie);
}
