package com.jwt.security.controller;

import com.jwt.security.payload.RandomStuff;
import com.jwt.security.request.LoginRequest;
import com.jwt.security.response.LoginResponse;
import com.jwt.security.security.JwtTokenProvider;
import com.jwt.security.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class MyRestController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public LoginResponse authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken((UserDetailsImpl) authentication.getPrincipal());

        return new LoginResponse(jwt);
    }

    @GetMapping("/random")
    public RandomStuff randomStuff() {
        return new RandomStuff("JWT hợp lệ mới thấy");
    }

}
