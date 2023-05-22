package com.jin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jin.mapper.MovieMapper;
import com.jin.pojo.Movie;
import com.jin.pojo.MovieType;
import com.jin.service.MovieTypeService;
import com.jin.mapper.MovieTypeMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public void updateTwo(Integer id, String movieType) {
        //获取type表的数据进行更改
        MovieType movieType1 = this.baseMapper.selectById(id);
        String firstType = movieType1.getMovieType();

        //更改movie表
        Movie movie = movieMapper.selectOne(new LambdaQueryWrapper<Movie>().eq(Movie::getMovieType, firstType));
        movie.setMovieType(movieType);
        movieMapper.update(movie,new LambdaQueryWrapper<Movie>().eq(Movie::getMovieType,firstType));

        //更改type表
        movieType1.setMovieType(movieType);
        this.baseMapper.update(movieType1,new LambdaQueryWrapper<MovieType>().eq(MovieType::getId,id));

    }

    @Override
    public void removeType(Integer id) {
        //删除，要判断是否为0，为0的话删除就是逻辑删除
        MovieType movieType = this.baseMapper.selectById(id);

        Integer number = movieType.getNumber();

        if (number == 0){
            this.baseMapper.delete(new LambdaQueryWrapper<MovieType>().eq(MovieType::getId,id));
            return;
        }
        //正常删除
        number = number-1;
        movieType.setNumber(number);
        this.baseMapper.update(movieType,new LambdaQueryWrapper<MovieType>().eq(MovieType::getId,id));

    }

}




