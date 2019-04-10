package com.itcuc.base.service;

import com.itcuc.base.entity.Manager;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface ManagerService {
    Manager findById(String id);
    Manager findByUsername(String username);
    Page<Manager> findManagerList(Map<String,String> map);

    void update(Manager manager);
    void loadRoles(Manager manager);
}
