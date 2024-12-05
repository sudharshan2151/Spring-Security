package com.springSecurity.SpringSecurityLearing.controllers;

import com.springSecurity.SpringSecurityLearing.model.Users;
import com.springSecurity.SpringSecurityLearing.service.UsersService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public String login(@RequestBody Users user){
        System.out.println("Inside Login");
        return  verify(user);
    }


    @PostMapping("/register")
    public ResponseEntity<Users> addUser(@RequestBody Users user){
        Users users = usersService.addUser(user);
        return new ResponseEntity<>(users, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity getUsers(){
        List<Users> allUsers = usersService.getAllUsers();
        return new ResponseEntity(allUsers,HttpStatus.OK);
    }


    public String verify(Users user) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
        if(authenticate.isAuthenticated())
            return "Sucessfully Logged in";
        else
            return "Login Failed";
    }
}
