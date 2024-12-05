package com.springSecurity.SpringSecurityLearing.service;

import com.springSecurity.SpringSecurityLearing.model.Users;
import com.springSecurity.SpringSecurityLearing.repo.UsersRepo;
import com.springSecurity.SpringSecurityLearing.util.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService implements UserDetailsService {

    @Autowired
    private UsersRepo usersRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepo.findByUserName(username);
        if(user==null){
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("User not found "+" "+username);
        }
        System.out.println(user);
        return new MyUserDetails(user);
    }

    public Users addUser(Users user){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
        user.setPassword(user.getPassword());
        return usersRepo.save(user);
    }

    public List<Users> getAllUsers() {
        return usersRepo.findAll();
    }


}
