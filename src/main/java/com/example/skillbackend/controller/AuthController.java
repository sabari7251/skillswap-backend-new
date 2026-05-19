package com.example.skillbackend.controller;

import com.example.skillbackend.dto.AuthResponse;
import com.example.skillbackend.dto.LoginRequest;
import com.example.skillbackend.dto.RegisterRequest;
import com.example.skillbackend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest regBody){
        return ResponseEntity.status(HttpStatus.OK).body(authService.register(regBody));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest logBody){
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(logBody));
    }
}
