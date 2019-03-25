package com.itcuc.news.service;

import com.itcuc.news.entity.Article;

import java.util.List;

public interface ArticleService {
    List<Article> list();

    /**
     * 删除一条
     * @param id
     * @return
     */
    String delete(String id);
}
