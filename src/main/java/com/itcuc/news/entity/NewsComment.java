package com.itcuc.news.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "news_comment")
public class NewsComment {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "news_id")
    private String newsId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "content")
    private String content;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "status")
    private Integer status;
}
