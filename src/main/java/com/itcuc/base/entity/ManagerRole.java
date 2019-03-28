package com.itcuc.base.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "base_rel_manager_role")
public class ManagerRole implements Serializable {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "manager_id")
    private String managerId;

    @Column(name = "role_id")
    private String roleId;
}
