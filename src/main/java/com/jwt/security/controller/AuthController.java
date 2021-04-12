package com.jwt.security.controller;

import com.jwt.security.payload.RandomStuff;
import com.jwt.security.request.LoginRequest;
import com.jwt.security.response.LoginResponse;
import com.jwt.security.security.jwt.JwtTokenProvider;
import com.jwt.security.security.model.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken((UserDetailsImpl) authentication.getPrincipal());

        return new ResponseEntity<>(new LoginResponse(jwt), HttpStatus.OK);
    }

    @GetMapping("/random")
    public RandomStuff randomStuff() {
        return new RandomStuff("JWT hợp lệ mới thấy");
    }

    @PostMapping("/helloworld")
    public ResponseEntity<?> hello(@RequestBody LoginRequest loginRequest) {
        System.out.println("run helloworld");

        return new ResponseEntity<>(new LoginRequest(loginRequest.getUsername(), loginRequest.getPassword()), HttpStatus.OK);
    }
}
