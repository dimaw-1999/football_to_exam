package com.example.reactProject.Services;

import com.example.reactProject.entity.Roles;
import com.example.reactProject.entity.Users;
import com.example.reactProject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoders(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(s);
        if(user!=null){
            return user;
        }else{
            throw new UsernameNotFoundException("USER NOT FOUND");
        }
    }

    @Override
    public boolean addUser(Users user) {
        Users user1 = userRepository.findByEmail(user.getEmail());
        if(user1!=null) {
        return false;
    }

            Users user2 = new Users();
            user2.setEmail(user.getEmail());
            user2.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user2.setFull_name(user.getFull_name());
            ArrayList<Roles> roles = new ArrayList<>();
            roles.add(new Roles(1L,"ROLE_USER"));
            user2.setRoles(roles);
            userRepository.save(user2);
            return true;
}}