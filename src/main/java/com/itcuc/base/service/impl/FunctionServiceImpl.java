package com.itcuc.base.service.impl;

import com.itcuc.base.entity.Function;
import com.itcuc.base.repository.FunctionDao;
import com.itcuc.base.repository.RoleFunctionDao;
import com.itcuc.base.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FunctionServiceImpl implements FunctionService {
    @Autowired
    private FunctionDao functionDao;
    @Autowired
    private RoleFunctionDao roleFunctionDao;

    @Override
    public List<Function> queryAll() {
        return functionDao.queryNonAbstract();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void remove(String id) {
        functionDao.deleteById(id);
        roleFunctionDao.deleteByFunctionId(id);
    }
}
