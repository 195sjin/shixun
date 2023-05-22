package com.jin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jin.pojo.Movie;
import com.jin.pojo.MovieType;
import com.jin.service.MovieService;
import com.jin.mapper.MovieMapper;
import org.springframework.stereotype.Service;

/**
* @author 子衿啊
* @description 针对表【t_movie】的数据库操作Service实现
* @createDate 2023-05-22 13:02:32
*/
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie>
    implements MovieService{

}




