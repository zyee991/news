package com.itcuc.base.service.impl;

import com.itcuc.base.entity.Manager;
import com.itcuc.base.repository.ManagerDao;
import com.itcuc.base.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerDao managerDao;

    @Override
    @Cacheable(value = "manager",key = "#p0",keyGenerator = "nKeyGenerator")
    public Manager findById(String id) {
        return managerDao.findById(id).get();
    }
}
