package com.example.reactProject.rest;


import com.example.reactProject.Services.InterfaceLiga;
import com.example.reactProject.Services.UserService;
import com.example.reactProject.dto.JwtRequest;
import com.example.reactProject.dto.JwtResponse;
import com.example.reactProject.entity.*;
import com.example.reactProject.jwt.JWTTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class JwtAuthController {

    @Autowired
    private JWTTokenGenerator jwtTokenGenerator;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;




    @RequestMapping(value = "/auth")
    public ResponseEntity<?> auth(@RequestBody JwtRequest request) throws Exception{
      if(request.getFull_name()==null) {

          authenticate(request.getEmail(), request.getPassword());
          final UserDetails userDetails =
                  userService.loadUserByUsername(request.getEmail());

          final String token = jwtTokenGenerator.generateToken(userDetails);
          return ResponseEntity.ok(new JwtResponse(token));

      }
      else {
          Users users = new Users();
          users.setEmail(request.getEmail());
          users.setPassword(request.getPassword());
          users.setFull_name(request.getFull_name());
          boolean bool = userService.addUser(users);
          String f = "good";
          String g="bad";
          if(bool) {
              return ResponseEntity.ok(new JwtResponse(f));
          }
          else {
              return ResponseEntity.ok(new JwtResponse(g));
          }
      }
    }

    public void authenticate(String email, String password) throws Exception{

        try{

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        }catch (DisabledException e){
            throw new Exception("USER_DISABLED", e);
        }catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS", e);
        }

    }

    @RequestMapping(value = "/logout")
    public ResponseEntity<?> logout(@RequestBody JwtRequest request) throws Exception{


            authenticate(request.getEmail(), request.getPassword());
            final UserDetails userDetails =
                    userService.loadUserByUsername(request.getEmail());

            final String token = jwtTokenGenerator.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token));

        }

}