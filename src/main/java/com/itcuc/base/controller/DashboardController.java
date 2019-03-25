package com.itcuc.base.controller;

import com.itcuc.properties.Appinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("n")
public class DashboardController {
    @Autowired
    Appinfo appinfo;

    @RequestMapping(value = "index")
    public String index(ModelMap map) {
        map.put("projectName",appinfo.getProjectName());
        map.put("projectVersion",appinfo.getProjectVersion());
        return "index";
    }

    @RequestMapping(value = "login")
    public String login() {
        return "login";
    }
}
