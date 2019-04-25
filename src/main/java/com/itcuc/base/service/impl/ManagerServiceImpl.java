package com.itcuc.base.service.impl;

import com.itcuc.base.entity.Function;
import com.itcuc.base.entity.Manager;
import com.itcuc.base.entity.ManagerRole;
import com.itcuc.base.entity.Role;
import com.itcuc.base.repository.ManagerDao;
import com.itcuc.base.repository.ManagerRoleDao;
import com.itcuc.base.repository.RoleDao;
import com.itcuc.base.service.ManagerService;
import com.itcuc.common.utils.EncryptUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    ManagerRoleDao managerRoleDao;

    @Autowired
    RoleDao roleDao;

    @Override
    public void loadRoles(String id,Manager manager) {
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
    @Transactional(rollbackOn = Exception.class)
    public Manager save(Map<String, String> paramMap) {
        String username = paramMap.get("new_username");
        String nickname = paramMap.get("new_nickname");
        String rolesStr = paramMap.get("roles");
        String saveType = paramMap.get("saveType");
        Manager manager = null;
        if("add".equals(saveType)) {
            String password = paramMap.get("new_password");
            manager = new Manager();
            manager.setId(UUID.randomUUID().toString());
            manager.setUsername(username);
            manager.setNickname(nickname);
            manager.setPassword(EncryptUtils.md5(password,""));
            manager.setState("1");
            if(StringUtils.isNotBlank(rolesStr)) {
                saveManagerRole(rolesStr, manager);
            }
            managerDao.save(manager);
        } else if("edit".equals(saveType)) {
            String id = paramMap.get("id");
            manager = managerDao.findById(id).get();
            if(StringUtils.isNotBlank(username)) {
                manager.setUsername(username);
            }
            if(StringUtils.isNotBlank(nickname)) {
                manager.setNickname(nickname);
            }
            if(StringUtils.isNotBlank(rolesStr)) {
                managerRoleDao.deleteByManagerId(manager.getId());
                saveManagerRole(rolesStr, manager);
            }
            manager = managerDao.save(manager);
        }
        return manager;
    }

    @Override
    public void remove(String id) {
        managerDao.deleteById(id);
    }

    private void saveManagerRole(String rolesStr, Manager manager) {
        String[] roles = rolesStr.split(",");
        List<Role> roleList = new ArrayList<>();
        for (String roleId : roles) {
            ManagerRole managerRole = new ManagerRole();
            managerRole.setId(UUID.randomUUID().toString());
            managerRole.setRoleId(roleId);
            managerRole.setManagerId(manager.getId());
            managerRoleDao.save(managerRole);
            Role role = roleDao.getOne(roleId);
            roleList.add(role);
        }
        manager.setRoles(roleList);
    }

    @Override
    public Manager findById(String id) {
        Manager manager = managerDao.findById(id).get();
        loadRoles(manager.getId(),manager);
        return manager;
    }

    @Override
    public Manager findByUsername(String username) {
        return managerDao.findByUsername(username);
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
    public void update(String id,Manager manager) {
        managerDao.save(manager);
    }
}
