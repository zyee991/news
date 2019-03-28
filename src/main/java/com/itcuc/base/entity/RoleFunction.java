package com.itcuc.base.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "base_rel_role_function")
@Data
public class RoleFunction implements Serializable {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "role_id")
    private String roleId;

    @Column(name = "function_id")
    private String functionId;
}
