package com.example.skillbackend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    // LONG SECRET KEY
    private static final String SECRET =
            "mysupersecretkeymysupersecretkey123456";

    // Convert string into secure key
    private final SecretKey SECRET_KEY =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    //Generate Token
    public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .signWith(SECRET_KEY)
                .compact();

    }

    //Extract Email
    public String extractEmail(String token){
        Claims claims = Jwts.parser()

                .setSigningKey(SECRET_KEY)

                .parseClaimsJws(token)

                .getBody();

        return claims.getSubject();
    }

    public boolean isTokenValid(String token,String email){
        String extractedEmail=extractEmail(token);
        return extractedEmail.equals(email);
    }
}
