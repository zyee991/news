package com.itcuc.news.service.impl;

import com.itcuc.news.common.Result;
import com.itcuc.news.common.ResultCode;
import com.itcuc.news.entity.UserMain;
import com.itcuc.news.repository.UserDao;
import com.itcuc.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Result create(String username, String password) {
        String id = UUID.randomUUID().toString();
        String passwordEncode = bCryptPasswordEncoder.encode(password);
        UserMain user = new UserMain();
        user.setId(id);
        user.setPassword(passwordEncode);
        user.setUsername(username);
        user.setCreatedTime(new Date());
        UserMain userMain = userDao.save(user);
        return Result.success(userMain);
    }

    @Override
    public Result modify(UserMain userMain) {
        return null;
    }
}
