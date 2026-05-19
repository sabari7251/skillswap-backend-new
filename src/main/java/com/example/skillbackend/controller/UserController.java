package com.example.skillbackend.controller;

import com.example.skillbackend.dto.UpdateProfileRequest;
import com.example.skillbackend.model.User;
import com.example.skillbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/me")
    public String me() {

        return "Protected route accessed";
    }

    @PutMapping("/profile")
    public ResponseEntity<User> updateProfile(Authentication authentication, @RequestBody UpdateProfileRequest request) {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.updateProfile(request,authentication.getName()));
    }
}