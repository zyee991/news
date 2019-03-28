package com.itcuc.base.service.impl;

import com.itcuc.base.entity.Function;
import com.itcuc.base.entity.Manager;
import com.itcuc.base.entity.Role;
import com.itcuc.base.repository.ManagerDao;
import com.itcuc.base.repository.RoleDao;
import com.itcuc.base.service.ManagerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerDao managerDao;

    @Autowired
    RoleDao roleDao;

    @Override
    @Cacheable(value = "manager",key = "#p0")
    public Manager findById(String id) {
        Manager manager = managerDao.findById(id).get();
        if(manager != null) {
            List<Role> roleList = managerDao.findRoleByUserId(id);
            if(roleList != null && roleList.size() > 0) {
                for(Role role : roleList) {
                    List<Function> functions = roleDao.findFunctionsByRoleId(role.getId());
                    role.setFunctions(functions);
                }
            }
            manager.setRoles(roleList);
        }
        return manager;
    }

    @Override
    public Manager findByUsername(String username) {
        return managerDao.findByUsername(username);
    }

    @Override
    public Page<Manager> findManagerList(Map<String,String> map) {
        String pageIndex = map.get("pageIndex");
        String pageSize = map.get("pageSize");
        String username = map.get("username");

        Specification<Manager> spe = (Specification<Manager>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if(StringUtils.isNotBlank(username) && !"null".equals(username)) {
                list.add(criteriaBuilder.like(root.get("username"), "%" + username + "%"));
            }
            if(list.size() > 0) {
                Predicate[] predicates = new Predicate[]{};
                return criteriaBuilder.and(list.toArray(predicates));
            } else {
                return null;
            }
        };

        return managerDao.findAll(spe, PageRequest.of(Integer.valueOf(pageIndex),Integer.valueOf(pageSize), Sort.by(Sort.Order.asc("username"),Sort.Order.asc("id"))));
    }
}
