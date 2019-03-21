package com.itcuc.news.repository;

import com.itcuc.news.entity.NewsMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsMainDao extends JpaRepository<NewsMain,String>, JpaSpecificationExecutor<NewsMain> {
    @Query("select n from NewsMain n where n.status = 0")
    public List<NewsMain> queryAll();
}
