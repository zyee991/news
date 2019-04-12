package com.itcuc.base.service.impl;

import com.itcuc.base.entity.Function;
import com.itcuc.base.entity.Manager;
import com.itcuc.base.entity.Role;
import com.itcuc.base.repository.ManagerDao;
import com.itcuc.base.repository.RoleDao;
import com.itcuc.base.service.ManagerService;
import com.itcuc.common.utils.EncryptUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerDao managerDao;

    @Autowired
    RoleDao roleDao;

    @Override
    public void loadRoles(Manager manager) {
        if(manager != null) {
            List<Role> roleList = managerDao.findRoleByUserId(manager.getId());
            if(roleList != null && roleList.size() > 0) {
                for(Role role : roleList) {
                    List<Function> functions = roleDao.findFunctionsByRoleId(role.getId());
                    role.setFunctions(functions);
                }
            }
            manager.setRoles(roleList);
        }
    }

    @Override
    public Manager save(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        String nickname = paramMap.get("nickname");
        String roles = paramMap.get("roles");
        if(paramMap.containsKey("id")) {
            return null;
        } else {
            Manager manager = managerDao.findByUsername("username");
            if(manager != null) {
                return null;
            } else {
                String password = paramMap.get("password");
                manager = new Manager();
                manager.setId(UUID.randomUUID().toString());
                manager.setNickname(nickname);
                manager.setPassword(EncryptUtils.md5(password,""));
                manager.setState("1");
//                managerDao.save(manager);
                return manager;
            }
        }
    }

    @Override
    @Cacheable(value = "manager",key = "#p0")
    public Manager findById(String id) {
        Manager manager = managerDao.findById(id).get();
        loadRoles(manager);
        return manager;
    }

    @Override
    public Manager findByUsername(String username) {
        Manager manager = managerDao.findByUsername(username);
        return manager;
    }

    @Override
    public Page<Manager> findManagerList(Map<String,String> map) {
        String pageIndex = map.get("page");
        String pageSize = map.get("limit");
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

        return managerDao.findAll(spe, PageRequest.of(Integer.valueOf(pageIndex) - 1,Integer.valueOf(pageSize), Sort.by(Sort.Order.asc("username"),Sort.Order.asc("id"))));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void update(Manager manager) {
        managerDao.save(manager);
    }
}
