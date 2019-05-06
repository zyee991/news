package com.itcuc.base.service;

import com.itcuc.base.entity.Function;
import com.itcuc.base.entity.Manager;

import java.util.List;
import java.util.Map;

public interface FunctionService {
    List<Function> findNonAbstract();

    List<Function> findParents();

    void remove(String id);

    Function findById(String id);

    Function save(Map<String, String> paramMap, Manager manager);
}
