package com.springSecurity.SpringSecurityLearing.repo;

import com.springSecurity.SpringSecurityLearing.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users,Integer> {

    Users findByUserName(String name);
}
