package com.itcuc.news.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "news_article")
public class Article {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "author_id")
    private String authorId;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "content",columnDefinition="longtext")
    private String content;

    @Column(name = "content_text",columnDefinition="longtext")
    private String contentText;

    @Column(name = "source")
    private String source;

    @Column(name = "source_link")
    private String sourceLink;

    @Column(name = "source_author")
    private String sourceAuthor;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "status")
    private Integer status;
}
