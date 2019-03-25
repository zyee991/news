package com.itcuc.news.rest;

import com.itcuc.common.Result;
import com.itcuc.common.ResultCode;
import com.itcuc.news.entity.User;
import com.itcuc.news.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@Api(value = "登录/注册")
public class UserRestController {

    @Autowired
    UserService userService;

    @ApiOperation(value="注册", notes="注册")
    @ApiImplicitParams({
        @ApiImplicitParam(value = "用户名", required = true),
        @ApiImplicitParam(value = "密码", required = true)
    })
    @PostMapping("signup")
    public Result signup(@RequestParam String username,@RequestParam String password) {
        User user = userService.create(username,password);
        if(user != null) {
            return Result.success(user);
        } else {
            return Result.failure(ResultCode.USER_HAS_EXISTED);
        }
    }

    @ApiOperation(value="登录", notes="登录")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户名", required = true),
            @ApiImplicitParam(value = "密码", required = true)
    })
    @PostMapping("signin")
    public Result signin(@RequestParam String username,@RequestParam String password) {
        String token = userService.signin(username,password);
        if(StringUtils.isNotBlank(token)) {
            return Result.success(token);
        } else {
            return Result.failure(ResultCode.USER_LOGIN_ERROR);
        }
    }
}
