package com.itcuc.base.service.impl;

import com.itcuc.base.entity.Function;
import com.itcuc.base.entity.Manager;
import com.itcuc.base.entity.RoleFunction;
import com.itcuc.base.repository.FunctionDao;
import com.itcuc.base.repository.RoleFunctionDao;
import com.itcuc.base.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class FunctionServiceImpl implements FunctionService {
    @Autowired
    private FunctionDao functionDao;
    @Autowired
    private RoleFunctionDao roleFunctionDao;

    @Override
    public List<Function> findNonAbstract() {
        return functionDao.queryNonAbstract();
    }

    @Override
    public List<Function> findParents() {
        return functionDao.queryParents();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void remove(String id) {
        functionDao.deleteById(id);
        roleFunctionDao.deleteByFunctionId(id);
    }

    @Override
    public Function findById(String id) {
        return functionDao.getOne(id);
    }

    @Override
    public Function save(Map<String, String> paramMap, Manager manager) {
        String saveType = paramMap.get("saveType");
        String parentId = paramMap.get("parentId");
        String name = paramMap.get("name");
        String url = paramMap.get("url");
        Function function = null;
        if("add".equals(saveType)) {
            function = new Function();
            String id = UUID.randomUUID().toString();
            function.setId(id);
            function.setUrl(url);
            function.setName(name);
            function.setParentId(parentId);
            function.setCreatedTime(new Date());
            function.setCreatedUser(manager.getId());
            function = functionDao.save(function);
            // 新建菜单自动关联超级管理员
            RoleFunction roleFunction = new RoleFunction();
            roleFunction.setId(UUID.randomUUID().toString());
            roleFunction.setFunctionId(id);
            roleFunction.setRoleId("0");
            roleFunctionDao.save(roleFunction);
        } else if("edit".equals(saveType)) {
            String id = paramMap.get("id");
            function = functionDao.getOne(id);
            function.setUrl(url);
            function.setName(name);
            function.setParentId(parentId);
            function.setModifyTime(new Date());
            function.setModifyUser(manager.getId());
            function = functionDao.save(function);
        }
        return function;
    }
}
