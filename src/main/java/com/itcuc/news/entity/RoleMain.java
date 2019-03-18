package com.itcuc.news.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "role_main")
public class RoleMain {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;
}
