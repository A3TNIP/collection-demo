package com.islington.summer.collectiondemo.auth.controller;

import com.islington.summer.collectiondemo.auth.dto.RegisterDto;
import com.islington.summer.collectiondemo.auth.dto.UserLoginDto;
import com.islington.summer.collectiondemo.auth.service.AuthService;
import com.islington.summer.collectiondemo.auth.service.UserService;
import com.islington.summer.collectiondemo.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @Autowired
    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto dto) {
        return authService.generateToken(dto);
    }

    @PostMapping("register")
    public UserEntity register(@RequestBody RegisterDto registerDto) {
        return userService.register(registerDto);
    }
}
