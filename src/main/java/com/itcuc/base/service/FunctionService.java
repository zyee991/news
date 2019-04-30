package com.itcuc.base.service;

import com.itcuc.base.entity.Function;

import java.util.List;

public interface FunctionService {
    List<Function> queryAll();

    void remove(String id);
}
