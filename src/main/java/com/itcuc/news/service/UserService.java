package com.itcuc.news.service;

import com.itcuc.news.entity.UserMain;

public interface UserService {
    UserMain create(String username,String password);
    UserMain modify(UserMain userMain);

    String signin(String username, String password);
}
