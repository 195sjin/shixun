package com.jin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jin.pojo.News;
import com.jin.service.NewsService;
import com.jin.mapper.NewsMapper;
import org.springframework.stereotype.Service;

/**
* @author 子衿啊
* @description 针对表【t_news】的数据库操作Service实现
* @createDate 2023-05-22 13:02:42
*/
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News>
    implements NewsService{

}




