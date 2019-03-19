package com.itcuc.news.repository;

import com.itcuc.news.entity.UserMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<UserMain,String>, JpaSpecificationExecutor<UserMain> {
    @Query("select u from UserMain u where username = ?1")
    public UserMain findByUsername(String username);
}
