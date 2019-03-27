package com.itcuc.base.repository;

import com.itcuc.base.entity.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FunctionDao extends JpaRepository<Function,String>, JpaSpecificationExecutor<Function> {
}
