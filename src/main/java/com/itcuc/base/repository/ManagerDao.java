package com.itcuc.base.repository;

import com.itcuc.base.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ManagerDao extends JpaRepository<Manager,String>, JpaSpecificationExecutor<Manager> {
    @Query("select m from Manager m where m.username = ?1")
    Manager findByUsername(String username);
}
