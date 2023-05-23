package com.jin.controller;

import com.jin.common.Result;
import com.jin.mapper.MovieMapper;
import com.jin.pojo.MovieType;
import com.jin.service.MovieTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author xiaojin
 * @Date 2023/5/22 15:44
 */
@CrossOrigin
@RestController
@RequestMapping("/movieType")
public class MovieTypeController {
    @Resource
    private MovieTypeService movieTypeService;


    @PutMapping("/add")
    public Result<?> addType(@RequestParam("movieType") String movieType){

        //根据传来的参数查，是新增分俩情况，一组是原本就有，一组是原本没有
        movieTypeService.select(movieType);

        return Result.success("新增成功");
    }

    //更改所用的id是type表的id
    @PostMapping("/update")
    public Result<?> update(@RequestParam("id") Integer id,
                             @RequestParam("movieType") String movieType){

        movieTypeService.updateTwo(id,movieType);

        return Result.success("更改成功");
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable("id") Integer id){

        movieTypeService.removeType(id);

        return Result.success();
    }
    @GetMapping()
    public Result<?> getAllType(){
        List<MovieType> list = movieTypeService.list();
        return new Result<>(20000,"查询成功",list);
    }
}
