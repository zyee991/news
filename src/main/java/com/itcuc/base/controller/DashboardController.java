package com.itcuc.base.controller;

import com.itcuc.base.entity.Manager;
import com.itcuc.base.service.ManagerService;
import com.itcuc.common.Result;
import com.itcuc.common.ResultCode;
import com.itcuc.common.utils.CookieUtils;
import com.itcuc.common.utils.EncryptUtils;
import com.itcuc.properties.Appinfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

@Controller
@RequestMapping("n")
public class DashboardController {
    @Autowired
    Appinfo appinfo;

    @Autowired
    ManagerService managerService;

    @RequestMapping(value = "index")
    public String index(ModelMap map) {
        map.put("projectName",appinfo.getProjectName());
        map.put("projectVersion",appinfo.getProjectVersion());
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
        Manager manager = managerService.findByUsername(username);
        if(manager != null && EncryptUtils.verifyMd5(password,"",manager.getPassword())) {
            CookieUtils.addCookie("mninfo",manager.getId());
            return Result.success();
        } else {
            return Result.failure(ResultCode.USER_LOGIN_ERROR);
        }
    }

    @RequestMapping(value = "welcome")
    public String welcome(ModelMap map,HttpServletRequest request) {
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
