package com.jin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jin.common.Result;
import com.jin.pojo.News;
import com.jin.service.NewsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @PostMapping("/addNews")
    public Result<?> addOrUpdateNews(@RequestBody News news){
        Integer id = news.getId();
        if (id == null){
            //id为null，就是增加
            newsService.save(news);
            return Result.success();
        }
        //id 不为null，就是修改
        newsService.update(news,new LambdaQueryWrapper<News>().eq(News::getId,id));
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable("id") Integer id){
        //删除估计也是逻辑删除

        newsService.removeById(id);

        return Result.success();

    }


}
