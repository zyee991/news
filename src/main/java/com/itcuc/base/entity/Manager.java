package com.itcuc.base.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "base_manager")
@Entity
@Data
public class Manager implements Serializable {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "login_time")
    private Date loginTime;

    @Column(name = "login_addr")
    private String loginAddr;

    @Column(name = "state",length = 1)
    private String state;

    @Transient
    private List<Role> roles;
}
