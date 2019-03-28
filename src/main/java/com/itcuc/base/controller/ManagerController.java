package com.itcuc.base.controller;

import com.itcuc.base.entity.Manager;
import com.itcuc.base.service.ManagerService;
import com.itcuc.common.Result;
import com.itcuc.common.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("n/manager")
public class ManagerController {

    @Autowired
    ManagerService managerService;

    @RequestMapping("list")
    public String list() {
        return "manager/list";
    }

    @RequestMapping("data")
    @ResponseBody
    public Result data(@RequestParam Map<String,String> paramMap) {
        Page<Manager> page = managerService.findManagerList(paramMap);
        if(page != null) {
            return Result.success(page);
        } else {
            return Result.failure(ResultCode.RESULE_DATA_NONE);
        }
    }
}
