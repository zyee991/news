package com.itcuc.base.controller;

import com.itcuc.base.entity.Manager;
import com.itcuc.base.entity.Role;
import com.itcuc.base.service.ManagerService;
import com.itcuc.base.service.RoleService;
import com.itcuc.common.Result;
import com.itcuc.common.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("n/manager")
public class ManagerController {

    @Autowired
    ManagerService managerService;
    @Autowired
    RoleService roleService;
    @Autowired
    HttpServletRequest request;

    private static final String VIEW_LIST = "manager/list";
    private static final String VIEW_ADD = "manager/add";
    private static final String VIEW_EDIT = "manager/edit";

    @RequestMapping("list")
    public String list() {
        return VIEW_LIST;
    }

    @RequestMapping("data")
    @ResponseBody
    public Result data(@RequestParam Map<String,String> paramMap) {
        Page<Manager> page = managerService.findManagerList(paramMap);
        if(page != null) {
            List<Manager> managers = page.getContent();
            for(Manager manager:managers) {
                managerService.loadRoles(manager.getId(),manager);
            }
            return Result.success(managers);
        } else {
            return Result.failure(ResultCode.RESULE_DATA_NONE);
        }
    }

    @RequestMapping("add")
    public String add(ModelMap map) {
        List<Role> roles = roleService.findAll();
        map.put("roles",roles);
        return VIEW_ADD;
    }

    @RequestMapping("edit")
    public String edit(@RequestParam String id, ModelMap map) {
        Manager manager = managerService.findById(id);
        List<Role> roles = roleService.findAll();
        if(manager != null) {
            map.put("manager",manager);
            for(Role role:roles) {
                if(manager.getRoles().contains(role)) {
                    role.setChecked(true);
                } else {
                    role.setChecked(false);
                }
            }
            map.put("roles",roles);
        }
        return VIEW_EDIT;
    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestParam Map<String,String> paramMap) {
        Manager manager = managerService.save(paramMap);
        if(manager != null) {
            return Result.success();
        } else {
            return Result.failure(ResultCode.USER_HAS_EXISTED);
        }
    }

    @RequestMapping(value = "remove",method = RequestMethod.POST)
    @ResponseBody
    public Result remove(@RequestParam String id) {
        managerService.remove(id);
        return Result.success();
    }
}
