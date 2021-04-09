package com.jwt.security.controller;

import com.jwt.security.request.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/abc")
public class TestController {
//    @PostMapping("/xyz")
//    public ResponseEntity<Object> test(@RequestBody LoginRequest loginRequest) {
    @PostMapping("/xyz")
    Map<String, String> home() {
        Map<String, String> map = new HashMap<>();
        map.put("hello", "world");
        return map;
    }
}
