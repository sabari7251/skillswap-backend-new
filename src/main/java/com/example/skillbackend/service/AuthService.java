package com.example.skillbackend.service;

import com.example.skillbackend.dto.AuthResponse;
import com.example.skillbackend.dto.LoginRequest;
import com.example.skillbackend.dto.RegisterRequest;
import com.example.skillbackend.model.User;
import com.example.skillbackend.repository.UserRepository;
import com.example.skillbackend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest request){

        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email Already Exist");
        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        //  Encrypt Password
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        //Save User
        userRepository.save(user);

        //generate Token
        String token = jwtService.generateToken(user.getEmail());

        //Return Response
        return new AuthResponse(token, user.getName(), user.getEmail());


    }

    public AuthResponse login(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()->new RuntimeException("User Not Found"));

        boolean passwordMatches = passwordEncoder.matches(request.getPassword(),user.getPassword());

        if(!passwordMatches)    throw new RuntimeException("Invalid Password");

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(
                token,
                user.getName(),
                user.getEmail()
        );

    }
}
