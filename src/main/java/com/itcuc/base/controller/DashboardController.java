package com.itcuc.base.controller;

import com.itcuc.base.entity.Function;
import com.itcuc.base.entity.Manager;
import com.itcuc.base.entity.Role;
import com.itcuc.base.service.ManagerService;
import com.itcuc.common.Result;
import com.itcuc.common.ResultCode;
import com.itcuc.common.utils.CookieUtils;
import com.itcuc.common.utils.EncryptUtils;
import com.itcuc.common.utils.IpAddressUtil;
import com.itcuc.properties.Appinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Controller
@RequestMapping("n")
public class DashboardController {
    @Autowired
    Appinfo appinfo;

    @Autowired
    ManagerService managerService;

    @Autowired
    HttpServletRequest request;

    @RequestMapping(value = "index")
    public String index(ModelMap map) {
        map.put("projectName",appinfo.getProjectName());
        map.put("projectVersion",appinfo.getProjectVersion());
        Manager manager = (Manager) request.getAttribute("manager");

        List<Role> roleList = manager.getRoles();
        List<Function> functions = new ArrayList<>();
        for(Role role :roleList) {
            List<Function> functionList = role.getFunctions();
            for(Function function:functionList) {
                if(!functions.contains(function)) {
                    functions.add(function);
                }
            }
        }

        List<Function> parentFunctions = new ArrayList<>();
        List<Function> childFunctions = new ArrayList<>();
        for(Function function:functions) {
            if("abstract".equals(function.getId())) {
                continue;
            }
            if("abstract".equals(function.getParentId())) {
                parentFunctions.add(function);
            } else {
                childFunctions.add(function);
            }
        }
        map.put("parentFunctions",parentFunctions);
        map.put("childFunctions",childFunctions);
        map.put("copyright",appinfo.getCopyright());
        return "index";
    }

    @RequestMapping(value = "login")
    public String login(ModelMap map) {
        map.put("projectName",appinfo.getProjectName());
        map.put("projectVersion",appinfo.getProjectVersion());
        return "login";
    }

    @RequestMapping(value = "signin")
    @ResponseBody
    public Result signin(String username, String password) {
        String ip = IpAddressUtil.getIpAdrress(request);
        Manager manager = managerService.findByUsername(username);
        if(manager != null && EncryptUtils.verifyMd5(password,"",manager.getPassword())) {
            manager.setLoginTime(new Date());
            manager.setLoginAddr(ip);
            managerService.update(manager);
            CookieUtils.addCookie("mninfo",manager.getId());
            return Result.success();
        } else {
            return Result.failure(ResultCode.USER_LOGIN_ERROR);
        }
    }

    @RequestMapping(value = "welcome")
    public String welcome(ModelMap map) {
        Manager manager = (Manager) request.getAttribute("manager");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss");
        map.put("nickname",manager.getNickname() == null ? manager.getUsername() : manager.getNickname());
        map.put("currentTime",sdf.format(new Date()));
        Properties properties = System.getProperties();
        String osName = properties.getProperty("os.name");
        String javaVersion = properties.getProperty("java.runtime.version");
        map.put("osName",osName);
        map.put("javaVersion",javaVersion);
        map.put("projectVersion",appinfo.getProjectVersion());
        return "welcome";
    }
}
