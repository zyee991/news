package com.itcuc.news.service.impl;

import com.itcuc.news.entity.Article;
import com.itcuc.news.repository.ArticleDao;
import com.itcuc.news.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleDao articleDao;

    @Override
    public List<Article> list() {
        return articleDao.queryAll();
    }

    @Override
    public String delete(String id) {
        try {
            articleDao.deleteById(id);
        } catch (Exception e) {
            return null;
        }
        return id;
    }
}
