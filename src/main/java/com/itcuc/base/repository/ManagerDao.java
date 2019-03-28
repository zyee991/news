package com.itcuc.base.repository;

import com.itcuc.base.entity.Manager;
import com.itcuc.base.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ManagerDao extends JpaRepository<Manager,String>, JpaSpecificationExecutor<Manager> {
    @Query("select m from Manager m where m.username = ?1")
    Manager findByUsername(String username);

    @Query("select r from Role r join ManagerRole mr on r.id = mr.roleId where mr.managerId = ?1")
    List<Role> findRoleByUserId(String userId);
}
