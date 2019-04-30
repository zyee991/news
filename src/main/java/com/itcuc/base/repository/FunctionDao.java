package com.itcuc.base.repository;

import com.itcuc.base.entity.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FunctionDao extends JpaRepository<Function,String>, JpaSpecificationExecutor<Function> {
    @Query("SELECT f from Function f where id <> 'abstract'")
    List<Function> queryNonAbstract();
}
