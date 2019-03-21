package com.itcuc.news.rest;

import com.itcuc.news.common.Result;
import com.itcuc.news.common.ResultCode;
import com.itcuc.news.entity.NewsMain;
import com.itcuc.news.service.NewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("news")
@Api(value = "新闻接口")
public class NewsRestController {

    @Autowired
    NewsService newsService;

    @PostMapping("articles")
    public Result articles(String title,String content) {
        return null;
    }

    @GetMapping("articles")
    @ApiOperation(value = "查询新闻列表")
    public Result articles() {
        List<NewsMain> list = newsService.list();
        if(list != null) {
            return Result.success(list);
        } else {
            return Result.failure(ResultCode.DATA_IS_WRONG);
        }
    }
}
