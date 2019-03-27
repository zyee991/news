package com.itcuc.base.entity;

import lombok.Data;
import org.hibernate.annotations.Proxy;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "base_manager_role", inverseJoinColumns = @JoinColumn(name = "role_id"), joinColumns = @JoinColumn(name = "manager_id"))
    private List<Role> roles;
}
