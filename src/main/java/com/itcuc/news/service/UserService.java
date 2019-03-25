package com.itcuc.news.service;

import com.itcuc.news.entity.User;

public interface UserService {
    User create(String username, String password);
    User modify(User user);

    String signin(String username, String password);
}
