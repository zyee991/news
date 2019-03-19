package com.itcuc.news.service;

import com.itcuc.news.common.Result;
import com.itcuc.news.entity.UserMain;

public interface UserService {
    Result create(String username,String password);
    Result modify(UserMain userMain);
}
