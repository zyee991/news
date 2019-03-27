package com.itcuc.base.service.impl;

import com.itcuc.base.entity.Function;
import com.itcuc.base.repository.FunctionDao;
import com.itcuc.base.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    FunctionDao functionDao;

    @Override
    @Cacheable(value = "listByManager",key = "#p0")
    public List<Function> listByManager(String managerId) {
        return null;
    }
}
