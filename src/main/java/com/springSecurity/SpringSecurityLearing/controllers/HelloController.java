package com.springSecurity.SpringSecurityLearing.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {



    @GetMapping("/hello")
    public String getHello(HttpServletRequest http){
        return "Hello Word :)-"+" "+http.getSession().getId();
    }

    @GetMapping("/")
    public String  getWelcome(HttpServletRequest http){
        return "Welcome to New World"+" "+http.getSession().getId();
    }

}
