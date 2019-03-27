package com.itcuc.base.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "base_function")
public class Function implements Serializable {
    @Id
    @Column(name = "id")
    private String id;

    @OneToOne
    private Function parent;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "icon")
    private String icon;

    @Column(name = "created_user")
    private String createdUser;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "modify_user")
    private String modifyUser;

    @Column(name = "modify_time")
    private Date modifyTime;

    @ManyToMany(mappedBy = "functions")
    private List<Role> roleList;
}