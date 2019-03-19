package com.itcuc.news.rest;

import com.itcuc.news.common.Result;
import com.itcuc.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserRestController {

    @Autowired
    UserService userService;

    @PostMapping("signup")
    public Result signup(String username,String password) {
        return userService.create(username,password);
    }
}
