package com.itcuc.base.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "base_role")
@Entity
@Data
public class Role implements Serializable {
    @Id
    @Column(name = "id")
    private String role;

    @Column(name = "name")
    private String name;

    @Column(name = "created_user")
    private String createdUser;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "modify_user")
    private String modifyUser;

    @Column(name = "modify_time")
    private Date modifyTime;

    @ManyToMany
    @JoinTable(name = "base_role_function", inverseJoinColumns = @JoinColumn(name = "function_id"), joinColumns = @JoinColumn(name = "role_id"))
    private List<Function> functions;

    @ManyToMany(mappedBy = "roles")
    private List<Manager> managerList;
}
