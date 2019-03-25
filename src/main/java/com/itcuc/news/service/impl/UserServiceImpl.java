package com.itcuc.news.service.impl;

import com.itcuc.news.entity.User;
import com.itcuc.news.repository.UserDao;
import com.itcuc.news.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User create(String username, String password) {
        String id = UUID.randomUUID().toString();
        String passwordEncode = bCryptPasswordEncoder.encode(password);
        User user = new User();
        user.setId(id);
        user.setPassword(passwordEncode);
        user.setUsername(username);
        user.setCreatedTime(new Date());
        User userMain = userDao.save(user);
        return userMain;
    }

    @Override
    public User modify(User user) {
        return null;
    }

    @Override
    public String signin(String username, String password) {
        User user = userDao.findByUsername(username);
        if(user.getPassword().equals(bCryptPasswordEncoder.encode(password))) {
            String token = Jwts.builder()
                    .setSubject(username)
                    .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
                    .signWith(SignatureAlgorithm.HS512, "JwtSecret")
                    .compact();
            return "Bearer " + token;
        } else {
            return null;
        }
    }
}
