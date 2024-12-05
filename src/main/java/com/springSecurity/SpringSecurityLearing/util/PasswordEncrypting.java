package com.springSecurity.SpringSecurityLearing.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncrypting {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        String password = "1234";
        String encode = encoder.encode(password);
        System.out.println(encode);
    }
}
