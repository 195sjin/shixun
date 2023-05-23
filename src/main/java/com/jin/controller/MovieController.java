package com.jin.controller;

import com.jin.common.Result;
import com.jin.mapper.NewsMapper;
import com.jin.pojo.Movie;
import com.jin.pojo.News;
import com.jin.service.MovieService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author xiaojin
 * @Date 2023/5/22 15:04
 */
@CrossOrigin
@RestController
@RequestMapping("/movie")
public class MovieController {
    @Resource
    private MovieService movieService;

    //上传文件
    @PostMapping("/upload")
    public Result<?> upload(@RequestPart("multipartFile") MultipartFile multipartFile) throws IOException {
        //获取上传文件名
        String filename = multipartFile.getOriginalFilename();
        //获取文件后缀
        assert filename != null;
        String suffix = filename.substring(filename.lastIndexOf("."));
        //避免冲突，使用UUID
        String fileName = UUID.randomUUID().toString().replace("-", "").toLowerCase().concat(suffix);
        //保存路径
        String realPath = "D:/Springdaima/mvManager/src/main/resources/static/upload/".concat(fileName);
        //文件保存
        multipartFile.transferTo(new File(realPath));

        return Result.success("upload/".concat(fileName));
    }


    @PostMapping("/addMovie")
    public Result<?> addMovie(@RequestBody Movie movie){

        movieService.saveAndChange(movie);

        return Result.success(movie,"添加电影信息成功");
    }

    //哪个电影的新闻
    @PutMapping("/udNews/{id}")
    public Result<?> udNews(@PathVariable("id") Integer id, @RequestBody News news){

        movieService.change(id,news);

        return Result.success("修改新闻信息成功");
    }

    @PostMapping("/addNews/{id}")
    public Result<?> addNews(@PathVariable("id") Integer id,@RequestBody News news){

        movieService.saveNews(id,news);

        return Result.success("新增新闻成功");
    }

    @GetMapping
    public Result<?> getAllMovie(){
        List<Movie> list = movieService.list();
        return new Result<>(20000,"查询成功啦",list);
    }


}
