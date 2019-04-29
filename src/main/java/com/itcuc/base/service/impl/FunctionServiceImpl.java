package com.itcuc.base.service.impl;

import com.itcuc.base.entity.Function;
import com.itcuc.base.repository.FunctionDao;
import com.itcuc.base.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionServiceImpl implements FunctionService {
    @Autowired
    private FunctionDao functionDao;

    @Override
    public List<Function> queryAll() {
        return functionDao.findAll();
    }
}
