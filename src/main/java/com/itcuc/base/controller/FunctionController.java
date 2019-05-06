package com.itcuc.base.controller;

import com.itcuc.base.entity.Function;
import com.itcuc.base.entity.Manager;
import com.itcuc.base.service.FunctionService;
import com.itcuc.common.Result;
import com.itcuc.common.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<Function> functionList = functionService.findNonAbstract();
        return functionList == null ? Result.failure(ResultCode.RESULE_DATA_NONE) : Result.success(functionList);
    }

    @RequestMapping("remove")
    @ResponseBody
    public Result remove(String id) {
        functionService.remove(id);
        return Result.success();
    }

    @RequestMapping("add")
    public String add(@RequestParam String parentId, ModelMap map) {
        List<Function> functionList = functionService.findParents();
        map.put("parentId",parentId);
        map.put("functionList",functionList);
        return VIEW_ADD;
    }

    @RequestMapping("edit")
    public String edit(@RequestParam String id, ModelMap map) {
        Function function = functionService.findById(id);
        List<Function> functionList = functionService.findParents();
        map.put("function",function);
        map.put("functionList",functionList);
        return VIEW_EDIT;
    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestParam Map<String,String> paramMap) {
        Manager manager = (Manager) request.getAttribute("manager");
        Function function = functionService.save(paramMap,manager);
        if (function == null) {
            return Result.success();
        } else {
            return Result.failure(ResultCode.SAVE_FAILED);
        }
    }
}
