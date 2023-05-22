package com.jin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jin.mapper.MovieMapper;
import com.jin.pojo.Movie;
import com.jin.pojo.MovieType;
import com.jin.service.MovieTypeService;
import com.jin.mapper.MovieTypeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author 子衿啊
* @description 针对表【t_movie_type】的数据库操作Service实现
* @createDate 2023-05-22 13:02:38
*/
@Service
public class MovieTypeServiceImpl extends ServiceImpl<MovieTypeMapper, MovieType>
    implements MovieTypeService{
    @Resource
    private MovieMapper movieMapper;

    @Override
    public void select(String movieType) {

        LambdaQueryWrapper<MovieType> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MovieType::getMovieType,movieType);

        MovieType type = this.baseMapper.selectOne(wrapper);
        //没有查出来就是新增一条数据，查出来了就是修改
        if (type == null){
            MovieType movieType1 = new MovieType();
            movieType1.setMovieType(movieType);
            this.baseMapper.insert(movieType1);
            return;
        }
        //修改
        Integer number = type.getNumber();
        number++;
        type.setNumber(number);
        this.baseMapper.updateById(type);

    }

    @Override
    public void updateTwo(MovieType movieType) {

        //传过来的有id，就是修改数据
        this.baseMapper.update(movieType,new LambdaQueryWrapper<MovieType>().eq(MovieType::getMovieType,movieType.getMovieType()));

        //顺带更改movie表里面的数据
        String movieType1 = movieType.getMovieType();
        Movie movie = movieMapper.selectOne(new LambdaQueryWrapper<Movie>().eq(Movie::getMovieType, movieType1));



    }
}




