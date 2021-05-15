package com.example.reactProject.Services;

import com.example.reactProject.entity.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public boolean addUser(Users user);

}