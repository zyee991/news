package com.itcuc.base.service;

import com.itcuc.base.entity.Manager;

public interface ManagerService {
    Manager findById(String id);
    Manager findByUsername(String username);
}
