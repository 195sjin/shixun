package com.jin.controller;

import com.jin.common.Result;
import com.jin.service.MovieService;
import freemarker.template.utility.StringUtil;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author xiaojin
 * @Date 2023/5/22 15:04
 */
@RestController
@RequestMapping("/movie")
public class MovieController {
    @Resource
    private MovieService movieService;


}
