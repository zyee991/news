package com.itcuc.news.repository;

import com.itcuc.news.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {
    @Query("select u from User u where username = ?1")
    public User findByUsername(String username);
}
