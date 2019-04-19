package com.itcuc.base.repository;

import com.itcuc.base.entity.ManagerRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ManagerRoleDao extends JpaRepository<ManagerRole,String>, JpaSpecificationExecutor<ManagerRole> {

    @Modifying
    @Query("delete from ManagerRole where managerId = ?1")
    void deleteByManagerId(String managerId);
}
