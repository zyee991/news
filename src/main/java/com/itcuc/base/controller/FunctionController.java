package com.itcuc.base.controller;

import com.itcuc.base.entity.Function;
import com.itcuc.base.service.FunctionService;
import com.itcuc.common.Result;
import com.itcuc.common.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("n/function")
public class FunctionController {
    @Autowired
    HttpServletRequest request;

    @Autowired
    private FunctionService functionService;

    private static final String VIEW_LIST = "function/list";
    private static final String VIEW_ADD = "function/add";
    private static final String VIEW_EDIT = "function/edit";

    @RequestMapping("list")
    public String list(){
        return VIEW_LIST;
    }

    @RequestMapping("data")
    @ResponseBody
    public Result data() {
        List<Function> functionList = functionService.queryAll();
        return functionList == null ? Result.failure(ResultCode.RESULE_DATA_NONE) : Result.success(functionList);
    }

    @RequestMapping("remove")
    @ResponseBody
    public Result remove(String id) {
        functionService.remove(id);
        return Result.success();
    }
}
