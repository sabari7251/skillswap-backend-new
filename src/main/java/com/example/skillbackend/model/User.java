package com.example.skillbackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(unique = true)
    private String email;

    private String password;
    private String bio;

    private String skillsOffered;

    private String skillsWanted;

    private LocalDateTime createdAt = LocalDateTime.now();

}
