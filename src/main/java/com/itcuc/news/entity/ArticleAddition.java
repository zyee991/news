package com.itcuc.news.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "news_article_addition")
public class ArticleAddition {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "article_id")
    private String articleId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "favorite")
    private Integer favorite;

    @Column(name = "readed")
    private Integer readed;

    @Column(name = "dislike")
    private Integer dislike;
}
