package com.itcuc.news.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "news_addition")
public class NewsAddition {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "news_id")
    private String newsId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "favorite")
    private Integer favorite;

    @Column(name = "readed")
    private Integer readed;

    @Column(name = "dislike")
    private Integer dislike;
}
