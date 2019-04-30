package com.itcuc.base.repository;

import com.itcuc.base.entity.RoleFunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RoleFunctionDao extends JpaRepository<RoleFunction,String>, JpaSpecificationExecutor<RoleFunction> {
    @Modifying
    @Query("delete from RoleFunction where functionId = ?1")
    void deleteByFunctionId(String functionId);

    @Modifying
    @Query("delete from RoleFunction where roleId = ?1")
    void deleteByRoleId(String roleId);
}
