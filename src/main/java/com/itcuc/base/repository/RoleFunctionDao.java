package com.itcuc.base.repository;

import com.itcuc.base.entity.RoleFunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleFunctionDao extends JpaRepository<RoleFunction,String>, JpaSpecificationExecutor<RoleFunction> {
}
