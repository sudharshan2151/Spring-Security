package com.springSecurity.SpringSecurityLearing.config;


import com.springSecurity.SpringSecurityLearing.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class BeanConfig {


    @Autowired
    private UsersService usersService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            return http.csrf(customizer->customizer.disable()).
                        authorizeHttpRequests(request->request
                                .requestMatchers("login","register")
                                .permitAll()
                                .anyRequest().authenticated()).
                        httpBasic(Customizer.withDefaults()).
                        sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
                        build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setPasswordEncoder(new BCryptPasswordEncoder(12));
        dao.setUserDetailsService(usersService);
        return dao;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }




}
