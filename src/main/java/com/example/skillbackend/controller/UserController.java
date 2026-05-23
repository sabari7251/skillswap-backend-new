package com.example.skillbackend.controller;

import com.example.skillbackend.dto.UpdateProfileRequest;
import com.example.skillbackend.model.User;
import com.example.skillbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PutMapping("/profile")
    public ResponseEntity<User> updateProfile(Authentication authentication, @RequestBody UpdateProfileRequest request) {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.updateProfile(request,authentication.getName()));
    }

    @GetMapping("/myprofile")
    public ResponseEntity<UpdateProfileRequest> getProfile(Authentication authentication){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getProfile(authentication.getName()));
    }

    @GetMapping("/allusers")
    public ResponseEntity<List<User>> getUsers(Authentication authentication){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers(authentication.getName()));
    }

    @GetMapping("/me")
    public ResponseEntity<User> getUser(Authentication authentication){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(authentication.getName()));
    }


}