package com.itcuc.news.rest;

import com.itcuc.news.common.Result;
import com.itcuc.news.entity.NewsMain;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("news")
public class NewsRestController {

    @PostMapping("articles")
    public Result articles(String title,String content) {
        return null;
    }
}
