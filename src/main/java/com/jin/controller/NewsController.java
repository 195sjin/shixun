package com.jin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jin.common.Result;
import com.jin.mapper.MovieMapper;
import com.jin.pojo.Movie;
import com.jin.pojo.News;
import com.jin.service.NewsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xiaojin
 * @Date 2023/5/22 21:35
 */
@CrossOrigin
@RestController
@RequestMapping("/news")
public class NewsController {
    @Resource
    private NewsService newsService;

    @Resource
    private MovieMapper movieMapper;


    //添加新闻
    @PostMapping("/addNews")
    public Result<?> addOrUpdateNews(@RequestBody News news){
        Integer id = news.getId();
        if (id == null){
            //id为null，就是增加
            newsService.save(news);
            return Result.success("添加新闻成功");
        }
        //id 不为null，就是修改
        newsService.update(news,new LambdaQueryWrapper<News>().eq(News::getId,id));
        return Result.success("修改新闻成功");
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable("id") Integer id){
        //判断该新闻是否属于某个电影
        String ids = id.toString();

        List<Movie> movies = movieMapper.selectList(new LambdaQueryWrapper<Movie>().like(Movie::getNewsId, ids));

        //属于某个电影
        for (Movie movie:movies) {
            List<String> stringList = Arrays.asList(movie.getNewsId().split(","));
            List<String> arrList = new ArrayList<String>(stringList);

            arrList.remove(ids);

            movie.setNewsId(String.join(",",arrList));
            movieMapper.updateById(movie);

        }

        //删除也是逻辑删除
        newsService.removeById(id);

        return Result.success("删除新闻成功");
    }
    @GetMapping
    public  Result<?> getAllNews(){
        List<News> list = newsService.list();
        return new Result<>(20000,"查询成功",list);
    }


}
