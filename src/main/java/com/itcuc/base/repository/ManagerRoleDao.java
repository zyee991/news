package com.itcuc.base.repository;

import com.itcuc.base.entity.ManagerRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ManagerRoleDao extends JpaRepository<ManagerRole,String>, JpaSpecificationExecutor<ManagerRole> {
}
