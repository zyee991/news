package com.itcuc.news.service.impl;

import com.itcuc.news.entity.NewsMain;
import com.itcuc.news.repository.NewsMainDao;
import com.itcuc.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsMainDao newsMainDao;

    @Override
    public List<NewsMain> list() {
        return newsMainDao.queryAll();
    }
}
