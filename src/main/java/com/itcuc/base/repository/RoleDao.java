package com.itcuc.base.repository;

import com.itcuc.base.entity.Function;
import com.itcuc.base.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleDao extends JpaRepository<Role,String>, JpaSpecificationExecutor<Role> {
    @Query("select f from Function f join RoleFunction rf on f.id = rf.functionId where rf.roleId = ?1")
    List<Function> findFunctionsByRoleId(String id);
}
