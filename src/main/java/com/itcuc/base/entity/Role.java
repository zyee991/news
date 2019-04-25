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
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_user")
    private String createdUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "modify_user")
    private String modifyUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_time")
    private Date modifyTime;

    @Transient
    private List<Function> functions;

    @Transient
    private List<Manager> managerList;

    @Transient
    private boolean checked;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Role role = (Role) o;

        return id.equals(role.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
