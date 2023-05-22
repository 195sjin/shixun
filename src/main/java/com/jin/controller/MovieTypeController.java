package com.jin.controller;

import com.jin.common.Result;
import com.jin.mapper.MovieMapper;
import com.jin.pojo.MovieType;
import com.jin.service.MovieTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author xiaojin
 * @Date 2023/5/22 15:44
 */
@RestController
@RequestMapping("/movieType")
public class MovieTypeController {
    @Resource
    private MovieTypeService movieTypeService;


    @PutMapping("/add")
    public Result<?> addType(@RequestParam("movieType") String movieType){

        //根据传来的参数查，是新增分俩情况，一组是原本就有，一组是原本没有
        movieTypeService.select(movieType);

        return Result.success();
    }

    @PostMapping("/update")
    private Result<?> update(@RequestBody MovieType movieType){
        //修改两张表
        movieTypeService.updateTwo(movieType);

        return Result.success();
    }
}
