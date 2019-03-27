package com.itcuc.base.service;

import com.itcuc.base.entity.Function;

import java.util.List;

public interface DashboardService {
    List<Function> listByManager(String managerId);
}
