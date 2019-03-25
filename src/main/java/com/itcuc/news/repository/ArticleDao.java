package com.itcuc.news.repository;

import com.itcuc.news.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleDao extends JpaRepository<Article,String>, JpaSpecificationExecutor<Article> {
    @Query("select n from Article n where n.status = 0")
    public List<Article> queryAll();
}
