package com.jin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jin.mapper.MovieTypeMapper;
import com.jin.mapper.NewsMapper;
import com.jin.pojo.Movie;
import com.jin.pojo.MovieType;
import com.jin.pojo.News;
import com.jin.service.MovieService;
import com.jin.mapper.MovieMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 子衿啊
* @description 针对表【t_movie】的数据库操作Service实现
* @createDate 2023-05-22 13:02:32
*/
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie>
    implements MovieService{

    @Resource
    private NewsMapper newsMapper;

    @Resource
    private MovieTypeMapper movieTypeMapper;

    @Override
    public void change(Integer newsId, News news) {

        newsMapper.update(news, new LambdaQueryWrapper<News>().eq(News::getId, newsId));

    }

    @Override
    public void saveNews(Integer id,News news) {
        newsMapper.insert(news);

        News news1 = newsMapper.selectOne(new LambdaQueryWrapper<News>().eq(News::getTitle, news.getTitle()));
        String newsIdIn = news1.getId().toString();

        Movie movie = this.baseMapper.selectById(id);
        String newsIdOld = movie.getNewsId();

        movie.setNewsId(newsIdOld +","+ newsIdIn);

        this.baseMapper.updateById(movie);


    }

    @Override
    public void saveAndChange(Movie movie) {
        this.baseMapper.insert(movie);

        String movieType = movie.getMovieType();
        MovieType movieType1 = movieTypeMapper.selectOne(new LambdaQueryWrapper<MovieType>().eq(MovieType::getMovieType, movieType));

        if (movieType1 == null){
            MovieType movieType2 = new MovieType();
            movieType2.setMovieType(movieType);
            movieType2.setNumber(1);
            movieTypeMapper.insert(movieType2);
            return;
        }
        Integer number = movieType1.getNumber();
        number++;
        movieType1.setNumber(number);
        movieTypeMapper.update(movieType1,new LambdaQueryWrapper<MovieType>().eq(MovieType::getMovieType,movieType));

    }

    @Override
    public Map<String, Object> getAllNews(Integer id) {
        Movie movie = this.baseMapper.selectById(id);

        List<String> idList = Arrays.asList(movie.getNewsId().split(","));

        Map<String, Object> map = new HashMap<>();
        for (String s:idList) {
            int i = Integer.parseInt(s);
            News news = newsMapper.selectById(i);
            map.put(s,news);
        }
        return map;
    }

    @Override
    public void updateM(Integer id, Movie movie) {

        String firstType = this.baseMapper.selectById(id).getMovieType();
        MovieType movieType3 = movieTypeMapper.selectOne(new LambdaQueryWrapper<MovieType>().eq(MovieType::getMovieType, firstType));

        String movieType = movie.getMovieType();

        MovieType movieType1 = movieTypeMapper.selectOne(new LambdaQueryWrapper<MovieType>().eq(MovieType::getMovieType, movieType));

        if (movieType1 == null){
            MovieType movieType2 = new MovieType();
            movieType2.setMovieType(movieType);
            movieType2.setNumber(1);
            movieTypeMapper.insert(movieType2);
            return;
        }

        if (!firstType.equals(movieType)){
            Integer number1 = movieType3.getNumber();
            number1--;
            movieType3.setNumber(number1);
            movieTypeMapper.update(movieType3,new LambdaQueryWrapper<MovieType>().eq(MovieType::getMovieType,firstType));

            Integer number = movieType1.getNumber();
            number++;
            movieType1.setNumber(number);
            movieTypeMapper.update(movieType1,new LambdaQueryWrapper<MovieType>().eq(MovieType::getMovieType,movieType));
        }

        this.baseMapper.update(movie,new LambdaQueryWrapper<Movie>().eq(Movie::getId,id));

    }
}




