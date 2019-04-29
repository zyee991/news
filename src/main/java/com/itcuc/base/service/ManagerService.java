package com.itcuc.base.service;

import com.itcuc.base.entity.Manager;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @author zy
 */
public interface ManagerService {
    Manager findById(String id);
    Manager findByUsername(String username);
    Page<Manager> findManagerList(Map<String,String> map);

    void update(String id,Manager manager);
    void loadRoles(String id,Manager manager);

    Manager save(Map<String, String> paramMap);

    void remove(String id);
}
