package com.example.skillbackend.service;

import com.example.skillbackend.dto.UpdateProfileRequest;
import com.example.skillbackend.model.User;
import com.example.skillbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User updateProfile(UpdateProfileRequest request,String email){
        User user = userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not Found"));
        user.setBio(request.getBio());
        user.setSkillsOffered(request.getSkillsOffered());
        user.setSkillsWanted(request.getSkillsWanted());
        return userRepository.save(user);
    }
}
