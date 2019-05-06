package com.itcuc.base.service.impl;

import com.itcuc.base.entity.Role;
import com.itcuc.base.repository.RoleDao;
import com.itcuc.base.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
