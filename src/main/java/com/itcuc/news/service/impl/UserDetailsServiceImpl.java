package com.itcuc.news.service.impl;

import com.itcuc.news.entity.UserMain;
import com.itcuc.news.repository.UserDao;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {

    private UserDao userDao;

    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserMain user = userDao.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new User(user.getUsername(),user.getPassword(), Collections.emptyList());
    }
}
