package com.jin.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jin.common.Result;
import com.jin.common.UploadGiteeImgBed;
import com.jin.mapper.MovieMapper;
import com.jin.mapper.MovieTypeMapper;
import com.jin.mapper.NewsMapper;
import com.jin.pojo.Movie;
import com.jin.pojo.MovieType;
import com.jin.pojo.News;
import com.jin.service.MovieService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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

    @Resource
    private MovieMapper movieMapper;



    //上传文件,给某个电影上传
    @PostMapping("/upload/{id}")
    public Result<?> upload(@PathVariable("id") Integer id,@RequestParam("file")MultipartFile multipartFile) throws IOException {

        //根据文件名生成指定的请求url
        String originalFilename = multipartFile.getOriginalFilename();
        assert originalFilename != null;
        String targetURL = UploadGiteeImgBed.createUploadFileUrl(originalFilename).concat(originalFilename);
        String fileUrl = UploadGiteeImgBed.uploadFileUrl(originalFilename).concat(originalFilename);

        //请求体封装
        Map<String, Object> uploadBodyMap = UploadGiteeImgBed.getUploadBodyMap(multipartFile.getBytes());
        //借助HttpUtil工具类发送POST请求
        HttpUtil.post(targetURL, uploadBodyMap);

        Movie movie = movieMapper.selectById(id);
        movie.setFilePath(fileUrl);
        movieMapper.update(movie,new LambdaQueryWrapper<Movie>().eq(Movie::getId,id));

        return  Result.success("上传影片内容成功");

    }


    @PostMapping("/addMovie")
    public Result<?> addMovie(@RequestBody Movie movie){

        movieService.saveAndChange(movie);

        return Result.success(movie,"添加电影信息成功");
    }

    //电影的哪一条新闻
    @PutMapping("/udNews/{id}")
    public Result<?> udNews(@PathVariable("id") Integer newsId, @RequestBody News news){

        movieService.change(newsId,news);

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

    @GetMapping("/getMn/{id}")
    public Result<?> getMoNews(@PathVariable("id") Integer id){
        Map<String, Object> data = movieService.getAllNews(id);
        if (data != null){
            return Result.success(data);
        }
        return Result.fail("查询电影新闻信息失败");
    }

    @PutMapping("/update/{id}")
    public Result<?> updateMo(@PathVariable("id") Integer id,@RequestBody Movie movie){

        movieService.updateM(id,movie);

        return Result.success("更新电影信息成功");
    }


}
