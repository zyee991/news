package com.itcuc.news.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloRest {
    @GetMapping("test")
    public String helloTest() {
        return "hello";
    }
}
